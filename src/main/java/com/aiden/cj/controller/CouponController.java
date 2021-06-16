package com.aiden.cj.controller;

import com.aiden.cj.constant.CommonResult;
import com.aiden.cj.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coupon")
public class CouponController {

    private final CouponService couponService;

    @Autowired
    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @GetMapping("/coupon_info/{id}")
    public CommonResult getCouponInfo(@PathVariable String id){
        return couponService.getCouponInfo(id);
    }

    @GetMapping("/mycoupons")
    public CommonResult mycoupons(@RequestParam String openid, String status) {
        return couponService.getMyCoupons(openid,status);
    }


}
