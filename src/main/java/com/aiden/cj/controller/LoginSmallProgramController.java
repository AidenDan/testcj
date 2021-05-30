package com.aiden.cj.controller;

import com.aiden.cj.constant.CommonResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author Aiden
 * @version 1.0
 * @description
 * @date 2021-5-27 20:08:33
 */

@CrossOrigin
@RestController
public class LoginSmallProgramController {
    private final RestTemplate restTemplate;
    @Value("${appId}")
    private String appId;
    @Value("${appSecret}")
    private String appSecret;

    @Autowired
    public LoginSmallProgramController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 用户进入小程序时, 获取用户的openid
     *
     * @param jsCode jsCode
     * @return 结果
     */
    @PostMapping("/loginSP/{jsCode}")
    public CommonResult loginSP(@PathVariable("jsCode") String jsCode) {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appId + "&secret=" + appSecret + "&js_code=" + jsCode + "&grant_type=authorization_code";
        String jsonString = restTemplate.getForObject(url, String.class);
        JSONObject jsonObject = JSON.parseObject(jsonString);
        String openid = (String) jsonObject.get("openid");
        return CommonResult.success().data("openid", openid);
    }
}
