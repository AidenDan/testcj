package com.aiden.cj.service;

import com.aiden.cj.constant.CommonResult;

/**
 * @author Aiden
 * @version 1.0
 * @description 抽奖
 * @date 2021-5-22 11:50:25
 */

public interface CjService {
    /**
     * 抽奖
     *
     * @param openid 微信用户id
     * @return 结果
     */
    public CommonResult getCjNumber(String openid);
}
