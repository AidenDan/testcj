package com.aiden.cj.service.impl;

import com.aiden.cj.constant.CommonResult;
import com.aiden.cj.constant.CouponStatus;
import com.aiden.cj.mapper.CouponMapper;
import com.aiden.cj.model.Coupon;
import com.aiden.cj.service.CjService;
import com.aiden.cj.util.RateRandomNumber;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Aiden
 * @version 1.0
 * @description
 * @date 2021-5-22 11:50:58
 */

@Slf4j
@Service
public class CjServiceImpl implements CjService {
    private final CouponMapper couponMapper;

    @Autowired
    public CjServiceImpl(CouponMapper couponMapper) {
        this.couponMapper = couponMapper;
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
//        String nowBegin = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        QueryWrapper<Coupon> wrapper02 = new QueryWrapper<>();
        wrapper02.ge("check_time", LocalDate.now());
        wrapper02.le("check_time", LocalDateTime.now());
        wrapper02.eq("user_id", openid);
        List<Coupon> checkCoupons = couponMapper.selectList(wrapper02);
        if (checkCoupons != null && checkCoupons.size() >= 3) {
            log.info("当前用户已经使用核销优惠卷3次, 今天不能继续抽奖, 用户id为:{}", openid);
            return CommonResult.fail().message("当前用户已经使用核销优惠卷3次, 今天不能继续抽奖");
        }

        // 抽奖逻辑 按照概率返回数字
        int rateNumber = RateRandomNumber.getRateNumber();
        return CommonResult.success().data("rateNumber", rateNumber);
    }
}
