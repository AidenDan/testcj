package com.aiden.cj.service.impl;

import com.aiden.cj.constant.CommonResult;
import com.aiden.cj.constant.CouponStatus;
import com.aiden.cj.constant.Coupons;
import com.aiden.cj.exception.BaseException;
import com.aiden.cj.mapper.CouponMapper;
import com.aiden.cj.model.Admin;
import com.aiden.cj.model.Coupon;
import com.aiden.cj.service.AdminService;
import com.aiden.cj.service.CjService;
import com.aiden.cj.service.CouponService;
import com.aiden.cj.util.RateRandomNumber;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Aiden
 * @version 1.0
 * @description
 * @date 2021-5-22 11:50:58
 */

@Slf4j
@Service
public class CjServiceImpl  implements CjService {

    private final CouponMapper couponMapper;

    private final CouponService couponService;

    private final AdminService adminService;

    @Autowired
    public CjServiceImpl(CouponMapper couponMapper, CouponService couponService, AdminService adminService) {
        this.couponMapper = couponMapper;
        this.couponService = couponService;
        this.adminService = adminService;
    }



    /**
     * 抽奖
     * 一天最多只能抽奖3次
     *
     * @param openid
     * @return
     */
    @Override
    public CommonResult getCjNumber(String openid) {
        // 先判断这个用户有没有优惠卷还没有使用(没有核销), 如果还有优惠卷未使用不能继续参与抽奖
        // 返回未被核销的优惠卷
        QueryWrapper<Coupon> wrapper01 = new QueryWrapper<Coupon>();
        wrapper01.eq("coupon_status", CouponStatus.UNUSED.getValue());
        wrapper01.eq("user_id", openid);
        List<Coupon> coupons = couponMapper.selectList(wrapper01);
        if (coupons != null && !coupons.isEmpty()) {
            log.info("当前用户有优惠卷还未使用, 不可抽奖, 用户id为:{}", openid);
            return CommonResult.success().data("coupons", coupons);
        }

        // 用户在当天已经抽奖且核销了3次，也不能继续抽奖
        QueryWrapper<Coupon> wrapper02 = new QueryWrapper<>();
        wrapper02.like("check_time", DateTime.now().toString("yyyy-MM-dd"));
        wrapper02.eq("user_id", openid);
        List<Coupon> checkCoupons = couponMapper.selectList(wrapper02);
        if (checkCoupons != null && checkCoupons.size() >= 3) {
            log.info("当前用户已经使用核销优惠卷3次, 今天不能继续抽奖, 用户id为:{}", openid);
            return CommonResult.fail().message("您已经使用核销优惠卷3次, 今天不能继续抽奖");
        }

        // 抽奖逻辑 按照概率返回数字
        int rateNumber = RateRandomNumber.getRateNumber();
        Coupon coupon = Coupon.builder().couponStatus(CouponStatus.UNUSED.getValue())
                                        .beginTime(DateTime.now().toString("yyyy-MM-dd"))
                                        .endTime(DateTime.now().plusDays(3).toString("yyyy-MM-dd"))
                                        .num(rateNumber)
                                        .userId(openid)
                                        .description(Coupons.getDesc(rateNumber))
                                        .quota(Coupons.getQuota(rateNumber))
                                        .build();
        couponService.save(coupon);
        return CommonResult.success().data("rateNumber", coupon);
    }

    @Override
    public CommonResult verifyLottery(String id, String openid) {
        LambdaQueryWrapper<Admin> query = Wrappers.lambdaQuery();
        query.eq(Admin::getOpenid,openid);
        Admin admin = adminService.getOne(query);
        Optional.ofNullable(admin).orElseThrow( () -> new BaseException("您不是核销人员无法核销优惠券"));

        LambdaQueryWrapper<Coupon> query1 = Wrappers.lambdaQuery();
        query1.eq(Coupon::getId,id);
        query1.eq(Coupon::getCouponStatus,CouponStatus.UNUSED.getValue());
        Coupon coupon = couponService.getOne(query1);
        Optional.ofNullable(coupon).orElseThrow(() -> new BaseException("未找到优惠券信息或优惠券已核销"));

        LambdaUpdateWrapper<Coupon> update = Wrappers.lambdaUpdate();
        update.eq(Coupon::getId, id);
        update.set(Coupon::getCheckTime, DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
        update.set(Coupon::getCouponStatus, CouponStatus.USED.getValue());
        couponService.update(update);
        return CommonResult.success();
    }


}
