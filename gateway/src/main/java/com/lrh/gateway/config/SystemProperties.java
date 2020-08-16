package com.lrh.gateway.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @auther lironghui
 * @date 2020/8/16
 */
@Configuration
@RefreshScope
@Data
@ConfigurationProperties(prefix = "gateway")
public class SystemProperties {
    private boolean checkLogin;
    private boolean checkAuth;
    private String dyanicRouteServerAddr;
    private String dyanicRouteDataId;
    private String dyanicRouteGroup;
    private List<String> excludeAuthUrls = new ArrayList<>();
}
