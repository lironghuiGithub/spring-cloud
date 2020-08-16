package com.lrh.dict.model;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import reactor.core.publisher.Mono;

import javax.persistence.*;

/**
 * @version 1.0
 * @auther lironghui
 * @date 2020/8/15
 */
@Entity(name = "sys_config")
public class SysConfig {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer nId;
    @Column(name = "s_key")
    private String sKey;
    @Column(name = "s_value")
    private String sValue;
    @Column(name = "s_desc")
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
