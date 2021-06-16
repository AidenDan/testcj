package com.aiden.cj.controller;

import com.aiden.cj.constant.CommonResult;
import com.aiden.cj.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 核销人员接口或管理员接口
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    /**
     * 获取核销人员信息
     * @param openid
     * @return
     */
    @GetMapping("/user_info")
    public CommonResult getUserInfo(@RequestParam String openid){
        return adminService.getUserInfo(openid);
    }

}
