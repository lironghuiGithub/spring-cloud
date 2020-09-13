package com.lrh.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.lrh.common.R;
import com.lrh.gateway.config.GatewayNacosProperties;
import com.lrh.gateway.util.AntPathUtil;
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
    private GatewayNacosProperties getwayNacosProperties;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (!getwayNacosProperties.isCheckLogin()) {
            return chain.filter(exchange);
        }
        String url;
        url = exchange.getRequest().getURI().getRawPath();
        if (AntPathUtil.matchAny(getwayNacosProperties.getExcludeAuthUrls(), url)) {
            chain.filter(exchange);
        }

        R loginR = checkLogin();
        if (R.isNotSuccess(loginR)) {
            return response(exchange.getResponse(), loginR);
        }
        if (!getwayNacosProperties.isCheckAuth()) {
            return chain.filter(exchange);
        }
        R authR = checkAuth();
        if (R.isNotSuccess(authR)) {
            return response(exchange.getResponse(), loginR);
        }
        return chain.filter(exchange);
    }

    //TODO 验证登录
    private R checkLogin() {
        return R.success();
    }

    //TODO 验证权限
    private R checkAuth() {
        return R.success();
    }

    private Mono<Void> response(ServerHttpResponse resp, R result) {
        resp.setStatusCode(HttpStatus.OK);
        resp.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        String content = JSON.toJSONString(result);
        DataBuffer buffer = resp.bufferFactory().wrap(content.getBytes(StandardCharsets.UTF_8));
        return resp.writeWith(Flux.just(buffer));
    }

    @Override
    public int getOrder() {
        return -100;
    }
}
