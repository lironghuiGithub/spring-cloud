/*
 *      Copyright (c) 2018-2028, Chill Zhuang All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the dreamlu.net developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: Chill 庄骞 (smallchill@163.com)
 */
package com.lrh.gateway.dynamic;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * 动态路由监听器
 *
 * @author Chill
 */
@Order
@Component
@Slf4j
public class DynamicRouteServiceListener{

    @Value("${spring.cloud.nacos.server-addr}")
    private String serverAddr;
    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${spring.cloud.nacos.config.group}")
    private String group;
    @Value("${spring.profiles.active}")
    private String env;
	@Autowired
    private  DynamicRouteService dynamicRouteService;

    /**
     * 监听Nacos下发的动态路由配置
     */
    @PostConstruct
    private void dynamicRouteServiceListener() {
        try {
            ConfigService configService = NacosFactory.createConfigService(serverAddr);
            String dataId = applicationName + "-" + env;
            configService.addListener(dataId, group, new Listener() {
                @Override
                public void receiveConfigInfo(String configInfo) {
                    if (configInfo != null) {
                        List<RouteDefinition> routeDefinitions = JSON.parseArray(configInfo, RouteDefinition.class);
                        log.error("@@@@@@@@@@@@@@@@@@@@@@@@@route definition change "+JSON.toJSONString(routeDefinitions));
                        dynamicRouteService.updateList(routeDefinitions);
                    }
                }
                @Override
                public Executor getExecutor() {
                    return null;
                }
            });
            String configInfo = configService.getConfig(dataId, group, 5000);
            if (configInfo != null) {
                List<RouteDefinition> routeDefinitions = JSON.parseArray(configInfo, RouteDefinition.class);
                dynamicRouteService.updateList(routeDefinitions);
            }
        } catch (NacosException ignored) {

        }
    }
}
