package com.aiden.cj.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author Aiden
 * @version 1.0
 * @description
 * @date 2021-5-22 10:16:56
 */

@Data
@Builder
public class User {
    // 用户的openid 唯一标识个用户
    private String openid;

}
