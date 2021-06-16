package com.aiden.cj.service;

import com.aiden.cj.constant.CommonResult;
import com.aiden.cj.model.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

public interface AdminService extends IService<Admin> {

    /**
     * 获取核销人员信息 , 如果不是核销人员 返回 null
     * @param openid
     * @return
     */
    CommonResult getUserInfo(String openid);
}
