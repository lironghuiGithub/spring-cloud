package com.lrh.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.lrh.common.ResultUtil;
import com.lrh.common.Result;
import com.lrh.gateway.config.SystemProperties;
import com.lrh.gateway.util.UrlUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @version 1.0
 * @auther lironghui
 * @date 2020/8/16
 */
@Slf4j
@Component
@Data
public class AuthFilter implements GlobalFilter, Ordered {
    @Autowired
    private SystemProperties systemProperties;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String url;
        if (systemProperties.isCheckLogin()) {
            url = exchange.getRequest().getURI().getRawPath();
            if (UrlUtil.matchAny(systemProperties.getExcludeAuthUrls(), url)) {
                chain.filter(exchange);
            }
            Result loginResult = checkLogin();
            if (loginResult != ResultUtil.SUCCESS) {
                return response(exchange.getResponse(), loginResult);
            }
            if (systemProperties.isCheckAuth()) {
                Result authResult = checkAuth();
                if (authResult != ResultUtil.SUCCESS) {
                    return response(exchange.getResponse(), authResult);
                }
            }
        }
        return chain.filter(exchange);

    }

    //TODO 验证登录
    private Result checkLogin() {
        return ResultUtil.renderSuccess();
    }

    //TODO 验证权限
    private Result checkAuth() {
        return ResultUtil.renderSuccess();
    }

    private Mono<Void> response(ServerHttpResponse resp, Result resultVO) {
        resp.setStatusCode(HttpStatus.OK);
        resp.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        String result = JSON.toJSONString(resultVO);
        DataBuffer buffer = resp.bufferFactory().wrap(result.getBytes(StandardCharsets.UTF_8));
        return resp.writeWith(Flux.just(buffer));
    }

    @Override
    public int getOrder() {
        return -100;
    }
}
