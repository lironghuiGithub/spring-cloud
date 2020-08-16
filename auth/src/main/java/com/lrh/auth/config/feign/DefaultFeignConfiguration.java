package com.lrh.auth.config.feign;

import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @version 1.0
 * @auther lironghui
 * @date 2020/8/15
 */
@Configuration
public class DefaultFeignConfiguration {
    @Bean
    public Retryer feignRetryer() {
        return new Retryer.Default(100,100,3);
    }
}
