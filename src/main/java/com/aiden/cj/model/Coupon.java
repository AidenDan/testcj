package com.aiden.cj.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Aiden
 * @version 1.0
 * @description 描述某个用户下的优惠卷信息
 * @date 2021-5-22 10:17:37
 */

@TableName(value = "coupon")
@Data
@Builder
public class Coupon {
    // 主键id
    @TableId
    private String id;
    // 优惠卷额度
    private Integer quota = 0;
    // 优惠卷描述
    private String description;
    // 优惠卷开始有效期
    private String beginTime;
    // 优惠卷最后有效期
    private String endTime;
    // 优惠卷的核销时间
    private String checkTime;
    // 优惠卷状态
    private String couponStatus;
    // 用户id 这个优惠卷属于哪一个用户
    private String userId;
    // index
    private Integer num;
}
