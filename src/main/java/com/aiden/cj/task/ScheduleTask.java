package com.aiden.cj.task;

import com.aiden.cj.constant.CouponStatus;
import com.aiden.cj.mapper.CouponMapper;
import com.aiden.cj.model.Coupon;
import com.aiden.cj.service.CouponService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Aiden
 * @version 1.0
 * @description 定时任务
 * @date 2021-5-26 10:54:14
 */

@Component
public class ScheduleTask {
    private final CouponMapper couponMapper;
    private final CouponService CouponService;

    @Autowired
    public ScheduleTask(CouponMapper couponMapper, CouponService couponService) {
        this.couponMapper = couponMapper;
        this.CouponService = couponService;
    }

    /**
     * 使用户的优惠卷过期, 每天0点执行一次
     * 什么样的优惠卷需要过期？
     * 一、没有核销
     * 二、当前时间已超过最大有效期
     */
    @Scheduled(cron = "0 0 0 * * ?")
    private void expireCoupons() {
        QueryWrapper<Coupon> wrapper = new QueryWrapper<>();
        wrapper.eq("coupon_status", CouponStatus.UNUSED.getValue());
        wrapper.le("end_time", LocalDateTime.now());
        List<Coupon> coupons = couponMapper.selectList(wrapper);
        if (coupons != null && !coupons.isEmpty()) {
            coupons.forEach(coupon -> {
                coupon.setCouponStatus(CouponStatus.EXPIRE.getValue());
                CouponService.updateCouponStatus(coupon);
            });
        }
    }
}
