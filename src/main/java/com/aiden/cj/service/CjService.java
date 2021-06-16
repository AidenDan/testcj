package com.aiden.cj.service;

import com.aiden.cj.constant.CommonResult;
import com.aiden.cj.model.Coupon;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author Aiden
 * @version 1.0
 * @description 抽奖
 * @date 2021-5-22 11:50:25
 */

public interface CjService  {
    /**
     * 抽奖
     *
     * @param openid 微信用户id
     * @return 结果
     */
    public CommonResult getCjNumber(String openid);

    /**
     * 核销优惠券
     * @param id
     * @return
     */
    CommonResult verifyLottery(String id, String openid);


}
