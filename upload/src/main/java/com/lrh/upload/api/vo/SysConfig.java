package com.lrh.upload.api.vo;

/**
 * @version 1.0
 * @auther lironghui
 * @date 2020/8/15
 */
public class SysConfig {
    private Integer nId;
    private String sKey;
    private String sValue;
    private String sDesc;

    public Integer getnId() {
        return nId;
    }

    public void setnId(Integer nId) {
        this.nId = nId;
    }

    public String getsKey() {
        return sKey;
    }

    public void setsKey(String sKey) {
        this.sKey = sKey;
    }

    public String getsValue() {
        return sValue;
    }

    public void setsValue(String sValue) {
        this.sValue = sValue;
    }

    public String getsDesc() {
        return sDesc;
    }

    public void setsDesc(String sDesc) {
        this.sDesc = sDesc;
    }
}
