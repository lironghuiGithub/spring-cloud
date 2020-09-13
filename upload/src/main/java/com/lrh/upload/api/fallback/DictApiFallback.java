package com.lrh.upload.api.fallback;

import com.lrh.common.spring.R;
import com.lrh.upload.api.DictApi;
import com.lrh.upload.api.vo.SysConfig;
import com.lrh.upload.common.ErrorCode;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @auther lironghui
 * @date 2020/8/22
 */
@Component
public class DictApiFallback implements DictApi {
    @Override
    public R<SysConfig> selectSysConfig(Integer id) {
        return R.fail(ErrorCode.UNKNOWN_ERROR);
    }
}
