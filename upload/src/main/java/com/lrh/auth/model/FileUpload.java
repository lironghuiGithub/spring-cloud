package com.lrh.auth.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @version 1.0
 * @auther lironghui
 * @date 2020/8/15
 */
@Entity(name = "file_upload")
public class FileUpload {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer nId;

    @Column(name = "s_name")
    private String sName;

    @Column(name = "s_path")
    private String sPath;

    @Column(name = "n_size")
    private Long nSize;

    @Column(name = "s_pattern")
    private String sPattern;

    @Column(name = "d_create")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dCreate;

    public Integer getnId() {
        return nId;
    }

    public void setnId(Integer nId) {
        this.nId = nId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsPath() {
        return sPath;
    }

    public void setsPath(String sPath) {
        this.sPath = sPath;
    }

    public Long getnSize() {
        return nSize;
    }

    public void setnSize(Long nSize) {
        this.nSize = nSize;
    }

    public String getsPattern() {
        return sPattern;
    }

    public void setsPattern(String sPattern) {
        this.sPattern = sPattern;
    }

    public Date getdCreate() {
        return dCreate;
    }

    public void setdCreate(Date dCreate) {
        this.dCreate = dCreate;
    }
}
