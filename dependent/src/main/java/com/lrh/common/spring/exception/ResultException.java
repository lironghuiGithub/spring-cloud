package com.lrh.common.spring.exception;

/**
 * @author lironghui
 * @version 1.0
 * @date 2019/11/3 14:15
 */
public class ResultException extends RuntimeException {
    private String code;
    private Object[] objects;

    public ResultException(String code) {
        this.code = code;
    }

    public ResultException(String code, String message) {
        this.code = code;
        this.objects = new Object[]{message};
    }

    public ResultException(String code, Object[] objects) {
        this.code = code;
        this.objects = objects;
    }

    public ResultException(String code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public ResultException(Throwable cause) {
        super(cause);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object[] getObjects() {
        return objects;
    }

    public void setObjects(Object[] objects) {
        this.objects = objects;
    }
}
