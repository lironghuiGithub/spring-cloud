package com.lrh.common;

/**
 * @version 1.0
 * @auther lironghui
 * @date 2020/8/16
 */
public class ResultUtil {
    public static final Result SUCCESS = new Result(0);

    public static Result renderSuccess() {
        return SUCCESS;
    }

    public static Result renderSuccess(Object obj) {
        return new Result(0, "", obj);
    }

    public static Result renderFail(int code, String msg) {
        return new Result(code, msg);
    }

    public static Result renderFail(int code, String msg, Object obj) {
        return new Result(code, msg, obj);
    }
}
