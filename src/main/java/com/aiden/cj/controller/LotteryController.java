package com.aiden.cj.controller;

import com.aiden.cj.constant.CommonResult;
import com.aiden.cj.service.CjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * 抽奖接口
 *
 */
@RestController
@RequestMapping("/lottery")
public class LotteryController {

    private final CjService cjService;

    @Autowired
    public LotteryController(CjService cjService) {
        this.cjService = cjService;
    }

    /**
     * 抽奖接口
     * @param openid
     * @return
     */
    @PostMapping("/draw")
    public CommonResult drawLottery(@RequestParam String openid){
        return cjService.getCjNumber(openid);
    }

    /**
     * 核销接口
     * @param id 奖品id
     * @param openid 核销人员id
     * @return
     */
    @PostMapping("/verify_lottery")
    public CommonResult verifyLottery(@RequestParam String id,@RequestParam String openid) {
        return cjService.verifyLottery(id,openid);
    }


}
