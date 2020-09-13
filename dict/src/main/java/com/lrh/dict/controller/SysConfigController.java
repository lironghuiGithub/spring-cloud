package com.lrh.dict.controller;

import com.lrh.common.spring.R;
import com.lrh.common.spring.exception.ResultException;
import com.lrh.dict.common.ErrorCode;
import com.lrh.dict.model.SysConfig;
import com.lrh.dict.service.ISysConfigService;
import com.lrh.mybatis.core.pagehelper.PageInfo;
import com.lrh.mybatis.core.pagehelper.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
/**
* @author lironghui
* @version 1.0
* @date 2020-08-29 21:50:19
*/
@Api(tags = "系统配置服务接口")
@RestController
public class SysConfigController{
    @Autowired
    private ISysConfigService sysConfigService;

    @ApiOperation(value = "分页查询")
    @ApiImplicitParams({
    @ApiImplicitParam(name = "currentPage", value = "currentPage", required = true, dataTypeClass = Integer.class, paramType = "query"),
    @ApiImplicitParam(name = "pageSize", value = "pageSize", required = true, dataTypeClass = Integer.class, paramType = "query")
    })
    @GetMapping("/sys/config/page")
    public Mono<R<PageInfo<SysConfig>>> selectPage(SysConfig sysConfig, PageVO pageVO) {
        return Mono.just(R.success(sysConfigService.selectPage(sysConfig, pageVO)));
    }

    @ApiOperation(value = "根据主键查询")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataTypeClass = Integer.class, paramType = "path")
    @GetMapping("/sys/config/{id}")
    public Mono<R<SysConfig>> selectById(@PathVariable Integer id) {
        if (1==1){
            throw new ResultException(ErrorCode.UNKNOWN_ERROR);
        }
        return Mono.just(R.success(sysConfigService.selectById(id)));
    }

    @ApiOperation(value = "新增")
    @ApiImplicitParam(name = "sysConfig", value = "sysConfig", required = true, dataTypeClass = SysConfig.class)
    @PutMapping("/sys/config")
    public Mono<R> save(@RequestBody SysConfig sysConfig) {
        sysConfigService.insertSelective(sysConfig);
        return Mono.just(R.success());
    }

    @ApiOperation(value = "修改")
    @ApiImplicitParam(name = "sysConfig", value = "sysConfig", required = true, dataTypeClass = SysConfig.class)
    @PostMapping("/sys/config")
    public Mono<R> update(@RequestBody SysConfig sysConfig) {
        sysConfigService.updateSelectiveById(sysConfig);
        return Mono.just(R.success());
    }

    @ApiOperation(value = "根据主键删除")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataTypeClass = Integer.class, paramType = "path")
    @DeleteMapping("/sys/config/{id}")
    public Mono<R> update(@PathVariable Integer id) {
        sysConfigService.deleteById(id);
        return Mono.just(R.success());
    }
}