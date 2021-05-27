package com.aiden.cj.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Aiden
 * @version 1.0
 * @description 配置类
 * @date 2021-5-22 10:07:37
 */

@EnableScheduling
@MapperScan(value = {"com.aiden.cj.mapper"})
@Configuration
@PropertySource(value = {"classpath:db.properties", "classpath:vx.properties"})
public class Config {
}
