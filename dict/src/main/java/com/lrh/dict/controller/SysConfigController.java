package com.lrh.dict.controller;

import com.lrh.common.spring.R;
import com.lrh.common.spring.web.exception.ResultException;
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
import java.util.List;

/**
* @author lironghui
* @version 1.0
* @date 2020-09-19 11:31:39
*/
@Api(tags = "系统配置服务接口")
@RestController
public class SysConfigController{
    @Autowired
    private ISysConfigService sysConfigService;

    @ApiOperation(value = "分页查询")
    @ApiImplicitParams({
    @ApiImplicitParam(name = "currentPage", value = "currentPage", required = true, dataType = "int", paramType = "query"),
    @ApiImplicitParam(name = "pageSize", value = "pageSize", required = true, dataType = "int", paramType = "query")
    })
    @GetMapping("/sys/config/page")
    public R<PageInfo<SysConfig>> selectPage(SysConfig sysConfig, PageVO pageVO) {
        if (1==1){
            throw new ResultException(ErrorCode.UNKNOWN_ERROR);
        }
        PageInfo<SysConfig> pageInfo = sysConfigService.selectPage(sysConfig, pageVO);
        return R.success(pageInfo);
    }
    @ApiOperation(value = "查询集合")
    @GetMapping("/sys/config/list")
    public R<List<SysConfig>> selectList(SysConfig sysConfig) {
        List<SysConfig> list = sysConfigService.selectList(sysConfig);
        return R.success(list);
    }
    @ApiOperation(value = "根据主键查询")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Integer", paramType = "path")
    @GetMapping("/sys/config/{id}")
    public R<SysConfig> selectById(@PathVariable Integer id) {
        SysConfig sysConfig = sysConfigService.selectById(id);
        return R.success(sysConfig);
    }

    @ApiOperation(value = "新增")
    @ApiImplicitParam(name = "sysConfig", value = "sysConfig", required = true, dataType = "SysConfig")
    @PutMapping("/sys/config")
    public R save(@RequestBody SysConfig sysConfig) {
        sysConfigService.insertSelective(sysConfig);
        return R.success();
    }

    @ApiOperation(value = "修改")
    @ApiImplicitParam(name = "sysConfig", value = "sysConfig", required = true, dataType = "SysConfig")
    @PostMapping("/sys/config")
    public R update(@RequestBody SysConfig sysConfig) {
        sysConfigService.updateSelectiveById(sysConfig);
        return R.success();
    }

    @ApiOperation(value = "根据主键删除")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Integer", paramType = "path")
    @DeleteMapping("/sys/config/{id}")
    public R update(@PathVariable Integer id) {
        sysConfigService.deleteById(id);
        return R.success();
    }
}