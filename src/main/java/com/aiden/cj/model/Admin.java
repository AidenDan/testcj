package com.aiden.cj.model;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Builder;
import lombok.Data;

/**
 * @author Aiden
 * @version 1.0
 * @description 核销人员信息表
 * @date 2021-5-26 12:04:13
 */

@Data
@Builder
public class Admin {

    @TableId
    private String id;

    /**
     * 核销人员姓名
     */
    private String name;

    /**
     * 核销人员联系电话
     */
    private String phone;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 上级id , 邀请核销人员的id
     */
    private String pid;

    /**
     * 核销人员 openid
     */
    private String openid;
}
