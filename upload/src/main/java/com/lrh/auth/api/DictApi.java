package com.lrh.auth.api;

import com.lrh.common.ErrorCode;
import com.lrh.common.ResultVO;
import com.lrh.common.util.ResultUtil;
import com.lrh.auth.api.vo.SysConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @version 1.0
 * @auther lironghui
 * @date 2020/8/15
 */
@FeignClient(value = "dict", fallback = DictApi.DictApiFallback.class)
public interface DictApi {
    @GetMapping("/sys/config/{id}")
    ResultVO<SysConfig> selectSysConfig(@PathVariable("id") Integer id);

    class DictApiFallback implements DictApi {
        @Override
        public ResultVO<SysConfig> selectSysConfig(Integer id) {
            return ResultUtil.renderFail(ErrorCode.UNKNOWN_ERROR, "UNKNON_ERROR");
        }
    }
}
