package com.lrh.common.util;

import com.lrh.common.ResultVO;

/**
 * @version 1.0
 * @auther lironghui
 * @date 2020/8/16
 */
public class ResultUtil {
    private static final ResultVO SUCCESS = new ResultVO(0);

    public static ResultVO renderSuccess() {
        return SUCCESS;
    }

    public static ResultVO renderSuccess(Object obj) {
        return new ResultVO(0, "", obj);
    }

    public static ResultVO renderFail(int code, String msg) {
        return new ResultVO(code, msg);
    }

    public static ResultVO renderFail(int code, String msg, Object obj) {
        return new ResultVO(code, msg, obj);
    }
}
