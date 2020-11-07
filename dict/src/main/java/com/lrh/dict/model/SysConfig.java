package com.lrh.dict.model;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
* @author lironghui
* @version 1.0
* @date 2020-08-29 21:45:58
*/
@ApiModel(description = "")
@Getter
@Setter
public class SysConfig implements Serializable{
    private static final long serialVersionUID = -1L;
    @ApiModelProperty(value="",dataType="Integer")
    private Integer nId;
    @ApiModelProperty(value="",dataType="String")
    private String sKey;
    @ApiModelProperty(value="",dataType="String")
    private String sValue;
    @ApiModelProperty(value="",dataType="String")
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