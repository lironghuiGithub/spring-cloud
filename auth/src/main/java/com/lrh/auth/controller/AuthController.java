package com.lrh.auth.controller;

import com.lrh.common.Result;
import com.lrh.common.ResultUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @version 1.0
 * @auther lironghui
 * @date 2020/8/16
 */
@RestController
public class AuthController {

    @PostMapping
    public Mono<Result> userAuth() {
        return Mono.just(ResultUtil.renderSuccess());
    }
}
