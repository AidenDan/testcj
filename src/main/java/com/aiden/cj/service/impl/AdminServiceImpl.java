package com.aiden.cj.service.impl;

import com.aiden.cj.constant.CommonResult;
import com.aiden.cj.mapper.AdminMapper;
import com.aiden.cj.model.Admin;
import com.aiden.cj.service.AdminService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {


    @Override
    public CommonResult getUserInfo(String openid) {
        LambdaQueryWrapper<Admin> query = Wrappers.lambdaQuery();
        query.eq(Admin::getOpenid, openid);
        Admin admin = getOne(query);
        return CommonResult.success().data("admin",admin);
    }
}
