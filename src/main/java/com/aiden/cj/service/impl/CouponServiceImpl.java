package com.aiden.cj.service.impl;

import com.aiden.cj.constant.CommonResult;
import com.aiden.cj.constant.CouponStatus;
import com.aiden.cj.mapper.CouponMapper;
import com.aiden.cj.model.Coupon;
import com.aiden.cj.service.CouponService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Aiden
 * @version 1.0
 * @description 优惠卷处理业务
 * @date 2021-5-22 10:31:53
 */

@Service
public class CouponServiceImpl extends ServiceImpl<CouponMapper, Coupon> implements CouponService {
    @Override
    public List<Coupon> queryUserCoupons(CouponStatus couponStatus, String userId) {
        if (StringUtils.isEmpty(userId)) {
            throw new RuntimeException("该用户不存在");
        }
        QueryWrapper<Coupon> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(couponStatus.getValue())) {
            queryWrapper.eq("coupon_status", couponStatus.getValue());
        }
        queryWrapper.eq("user_id", userId);
        return baseMapper.selectList(queryWrapper);
    }

    /**
     * 修改优惠卷状态
     *
     * @param coupon 优惠卷
     */
    @Override
    public void updateCouponStatus(Coupon coupon) {
        updateById(coupon);
    }

    /**
     * 添加优惠卷
     *
     * @param coupon 优惠卷
     */
    @Override
    public void addCoupon(Coupon coupon) {
        save(coupon);
    }

    @Override
    public CommonResult getCouponInfo(String id) {
        LambdaQueryWrapper<Coupon> query = Wrappers.lambdaQuery();
        query.eq(Coupon::getId, id);
        query.eq(Coupon::getCouponStatus,CouponStatus.UNUSED.getValue());
        Coupon coupon = getOne(query);
        return CommonResult.success().data("coupon",coupon);
    }

    @Override
    public CommonResult getMyCoupons(String openid, String status) {
        LambdaQueryWrapper<Coupon> query = Wrappers.lambdaQuery();
        query.eq(Coupon::getUserId, openid);
        query.eq(StringUtils.isNotBlank(status), Coupon::getCouponStatus, status);
        return CommonResult.success().data("list",list(query));
    }
}



