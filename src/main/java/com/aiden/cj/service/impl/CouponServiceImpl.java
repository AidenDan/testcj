package com.aiden.cj.service.impl;

import com.aiden.cj.constant.CouponStatus;
import com.aiden.cj.mapper.CouponMapper;
import com.aiden.cj.model.Coupon;
import com.aiden.cj.service.CouponService;
import com.aiden.cj.util.UUIDGenerator;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
        this.updateById(coupon);
    }

    /**
     * 添加优惠卷
     *
     * @param coupon 优惠卷
     */
    @Override
    public void addCoupon(Coupon coupon) {
        coupon.setId(UUIDGenerator.getUUID());
        // 设置优惠卷起始有效时间
        coupon.setBeginTime(LocalDateTime.now());
        // 设置优惠卷最后有效时间
        coupon.setEndTime(LocalDate.now().plusDays(3));
        this.save(coupon);
    }
}



