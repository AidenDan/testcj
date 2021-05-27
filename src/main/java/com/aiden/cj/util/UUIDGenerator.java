package com.aiden.cj.util;

import java.util.UUID;

/**
 * @author Aiden
 * @version 1.0
 * @description 生成uuid
 * @date 2021-5-26 18:22:48
 */
public class UUIDGenerator {
    /**
     * 获取id
     *
     * @return id
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
