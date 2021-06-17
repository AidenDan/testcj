package com.aiden.cj.job;

import com.aiden.cj.constant.CouponStatus;
import com.aiden.cj.mapper.CouponMapper;
import com.aiden.cj.model.Coupon;
import com.aiden.cj.service.CouponService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.log4j.Log4j2;
import org.joda.time.DateTime;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;


// 在成功执行了job类的execute方法后,更新JobDetail中JobDataMap的数据
@PersistJobDataAfterExecution
// 等待上一次任务执行完成，才会继续执行新的任务
@DisallowConcurrentExecution
@Log4j2
public class AwardExpiredJob  implements Job {


    private final CouponMapper couponMapper;

    private final CouponService CouponService;

    @Autowired
    public AwardExpiredJob(CouponMapper couponMapper, com.aiden.cj.service.CouponService couponService) {
        this.couponMapper = couponMapper;
        CouponService = couponService;
    }

    /**
     * 使用户的优惠卷过期, 每天0点执行一次
     * 什么样的优惠卷需要过期？
     * 一、没有核销
     * 二、当前时间已超过最大有效期
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("执行定时任务");
        LambdaQueryWrapper<Coupon> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Coupon::getCouponStatus, CouponStatus.UNUSED.getValue());
        wrapper.le(Coupon::getEndTime, DateTime.now().toString("yyyy-MM-dd"));
        List<Coupon> coupons = couponMapper.selectList(wrapper);
        if (coupons != null && !coupons.isEmpty()) {
            coupons.forEach(coupon -> {
                coupon.setCouponStatus(CouponStatus.EXPIRE.getValue());
                CouponService.updateCouponStatus(coupon);
            });
        }
        log.info("定时任务结束");
    }
}
