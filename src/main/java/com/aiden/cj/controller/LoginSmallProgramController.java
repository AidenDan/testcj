package com.aiden.cj.controller;

import com.aiden.cj.constant.CommonResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

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

    @Value("${jscode2session}")
    private String jscode2session;

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
        HashMap<String, String> params = Maps.newHashMap();
        params.put("jsCode",jsCode);
        String jsonString = restTemplate.getForObject(jscode2session, String.class,params);
        JSONObject jsonObject = JSON.parseObject(jsonString);
        String openid = (String) jsonObject.get("openid");
        return CommonResult.success().data("openid", openid);
    }
}
