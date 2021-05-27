package com.aiden.cj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Aiden
 * @version 1.0
 * @description
 * @date 2021-5-27 20:12:57
 */

@Configuration
public class RestConfig {

    @Bean("restTemplate")
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
