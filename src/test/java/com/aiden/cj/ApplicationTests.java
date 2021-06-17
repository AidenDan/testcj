//package com.aiden.cj;
//
//import com.aiden.cj.constant.CommonResult;
//import com.aiden.cj.constant.CouponStatus;
//import com.aiden.cj.model.Coupon;
//import com.aiden.cj.service.CjService;
//import com.aiden.cj.service.CouponService;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//@Slf4j
//@RunWith(SpringRunner.class)
//@SpringBootTest
//class ApplicationTests {
//
//    private final CouponService couponService;
//    private final CjService cjService;
//
//    @Autowired
//    public ApplicationTests(CouponService couponService, CjService cjService) {
//        this.couponService = couponService;
//        this.cjService = cjService;
//    }
//
//    /**
//     * 查询用户优惠卷
//     */
//    @Test
//    void contextLoads() {
//        List<Coupon> coupons = couponService.queryUserCoupons(CouponStatus.UNUSED, "123456789");
//        log.info(coupons.toString());
//    }
//
//    /**
//     * 获取随机数
//     */
//    @Test
//    public void testCj01() {
//        CommonResult result = cjService.getCjNumber("123456789");
//        System.err.println(result);
//    }
//
//}
