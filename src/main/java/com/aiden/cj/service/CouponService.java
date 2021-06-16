package com.aiden.cj.service;

import com.aiden.cj.constant.CommonResult;
import com.aiden.cj.constant.CouponStatus;
import com.aiden.cj.model.Coupon;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @author Aiden
 * @version 1.0
 * @description 查询用户的优惠卷信息
 * @date 2021-5-22 10:27:52
 */

public interface CouponService extends IService<Coupon> {
    /**
     * 查询用户的优惠卷
     * @param couponStatus 优惠卷状态
     * @param userId 用户id
     * @return 优惠卷
     */
    List<Coupon> queryUserCoupons(CouponStatus couponStatus, String userId);

    /**
     * 修改优惠卷状态
     *
     * @param coupon 优惠卷
     */
    void updateCouponStatus(Coupon coupon);

    /**
     * 添加优惠卷
     *
     * @param coupon 优惠卷
     */
    void addCoupon(Coupon coupon);

    /**
     * 获取优惠券信息
     * @return
     */
    CommonResult getCouponInfo(String id);

    /**
     * 获取我的优惠券
     * @param openid
     * @return
     */
    CommonResult getMyCoupons(String openid, String status);
}
