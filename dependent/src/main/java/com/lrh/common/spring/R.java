package com.lrh.common.spring;

/**
 * @version 1.0
 * @auther lironghui
 * @date 2020/8/16
 */
public class R<T> {
    private static final String SUCCESS_CODE = "0";
    private static final R SUCCESS = new R(SUCCESS_CODE, null, null);
    private String code;
    private String msg;
    private T obj;

    public R(String code, String msg, T obj) {
        this.code = code;
        this.msg = msg;
        this.obj = obj;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getObj() {
        return obj;
    }

    public static R success() {
        return SUCCESS;
    }

    public static <T> R<T> success(T data) {
        return new R<T>(SUCCESS_CODE, null, data);
    }

    public static <T> R<T> success(String msg, T data) {
        return new R<T>(SUCCESS_CODE, msg, data);
    }

    public static boolean isSuccess(R r) {
        return r != null ? r.code == SUCCESS_CODE : false;
    }

    public static boolean isNotSuccess(R r) {
        return !isSuccess(r);
    }

    public static R fail(String code) {
        return new R(code, null, null);
    }

    public static R fail(String code, String msg) {
        return new R(code, msg, null);
    }

    public static <T> R<T> fail(String code, String msg, T data) {
        return new R(code, msg, data);
    }


}
