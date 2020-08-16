package com.lrh.common;

/**
 * @version 1.0
 * @auther lironghui
 * @date 2020/8/16
 */
public class ResultVO<T> {
    private int code;
    private String msg;
    private T obj;

    public ResultVO() {
    }

    public ResultVO(int code) {
        this(code, "");
    }

    public ResultVO(int code, String msg) {
        this(code, msg, null);
    }

    public ResultVO(int code, String msg, T obj) {
        this.code = code;
        this.msg = msg;
        this.obj = obj;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
}
