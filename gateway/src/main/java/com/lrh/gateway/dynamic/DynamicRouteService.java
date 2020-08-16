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

import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.InMemoryRouteDefinitionRepository;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 动态路由业务类
 *
 * @author Chill
 */
@Service
public class DynamicRouteService implements ApplicationEventPublisherAware {

    private final RouteDefinitionWriter routeDefinitionWriter;

    private ApplicationEventPublisher publisher;

    public DynamicRouteService(RouteDefinitionWriter routeDefinitionWriter) {
        this.routeDefinitionWriter = routeDefinitionWriter;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    /**
     * 增加路由
     */
    public String save(RouteDefinition definition) {
        try {
            routeDefinitionWriter.save(Mono.just(definition)).subscribe();
            this.publisher.publishEvent(new RefreshRoutesEvent(this));
            return "save success";
        } catch (Exception e) {
            e.printStackTrace();
            return "save failure";
        }
    }

    /**
     * 更新路由
     */
    public String update(RouteDefinition definition) {
        try {
            this.routeDefinitionWriter.delete(Mono.just(definition.getId()));
            this.routeDefinitionWriter.save(Mono.just(definition)).subscribe();
            this.publisher.publishEvent(new RefreshRoutesEvent(this));
            return "update success";
        } catch (Exception e) {
            e.printStackTrace();
            return "update failure";
        }
    }

    /**
     * 更新路由
     */
    public String updateList(List<RouteDefinition> routeDefinitions) {
//		delNotExistRoutes( routeDefinitions);
        routeDefinitions.forEach(this::update);
        return "update done";
    }

    /**
     * 删除配置文件中不存在的路由
     */
    private void delNotExistRoutes(List<RouteDefinition> routeDefinitions) {
        List<String> rountIdList = routeDefinitions.stream().map(RouteDefinition::getId).collect(Collectors.toList());
        InMemoryRouteDefinitionRepository memoryRouteDefinitionRepository = (InMemoryRouteDefinitionRepository) this.routeDefinitionWriter;
        Flux<RouteDefinition> routeDefinitionFlux = memoryRouteDefinitionRepository.getRouteDefinitions();
        routeDefinitionFlux.map(new Function<RouteDefinition, Object>() {
            @Override
            public Object apply(RouteDefinition routeDefinition) {
                if (!rountIdList.contains(routeDefinition.getId())) {
                    delete(routeDefinition.getId());
                }
                return routeDefinition;
            }
        }).subscribe();
    }

    /**
     * 删除路由
     */
    public String delete(String id) {
        try {
            this.routeDefinitionWriter.delete(Mono.just(id));
            return "delete success";
        } catch (Exception e) {
            e.printStackTrace();
            return "delete failure";
        }
    }


}
