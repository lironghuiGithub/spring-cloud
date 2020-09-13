package com.lrh.upload.api;

import com.lrh.common.spring.R;
import com.lrh.upload.api.fallback.DictApiFallback;
import com.lrh.upload.api.vo.SysConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @version 1.0
 * @auther lironghui
 * @date 2020/8/15
 */
@FeignClient(value = "dict", fallback = DictApiFallback.class)
public interface DictApi {
    @GetMapping("/sys/config/{id}")
    R<SysConfig> selectSysConfig(@PathVariable("id") Integer id);
}
