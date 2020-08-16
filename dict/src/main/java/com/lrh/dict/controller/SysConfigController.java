package com.lrh.dict.controller;

import com.lrh.common.ResultVO;
import com.lrh.common.util.ResultUtil;
import com.lrh.dict.dao.SysConfigRepository;
import com.lrh.dict.model.SysConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @version 1.0
 * @auther lironghui
 * @date 2020/8/15
 */
@RestController
public class SysConfigController {
    @Autowired
    SysConfigRepository sysConfigRepository;

    @GetMapping("/sys/config/{id}")
    public Mono<ResultVO<SysConfig>> selectSysConfig(@PathVariable Integer id) {
        return Mono.just(ResultUtil.renderSuccess(sysConfigRepository.findById(id).get()));
    }
}
