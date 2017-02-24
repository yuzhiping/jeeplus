package com.jeeplus.blog.entities;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-23 17:10.
 */
public class BlogRes implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String resName;
    private String resType;
    private String resKey;
    private String resHash;
    private String resWebsiteid;
    private Timestamp resDate;
    private String resCreator;
    private Long resSize;

    @Override
    public String toString() {
        return "BlogRes{" +
                "id='" + id + '\'' +
                ", resName='" + resName + '\'' +
                ", resType='" + resType + '\'' +
                ", resKey='" + resKey + '\'' +
                ", resHash='" + resHash + '\'' +
                ", resWebsiteid='" + resWebsiteid + '\'' +
                ", resDate=" + resDate +
                ", resCreator='" + resCreator + '\'' +
                ", resSize=" + resSize +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getResType() {
        return resType;
    }

    public void setResType(String resType) {
        this.resType = resType;
    }

    public String getResKey() {
        return resKey;
    }

    public void setResKey(String resKey) {
        this.resKey = resKey;
    }

    public String getResHash() {
        return resHash;
    }

    public void setResHash(String resHash) {
        this.resHash = resHash;
    }

    public String getResWebsiteid() {
        return resWebsiteid;
    }

    public void setResWebsiteid(String resWebsiteid) {
        this.resWebsiteid = resWebsiteid;
    }

    public Timestamp getResDate() {
        return resDate;
    }

    public void setResDate(Timestamp resDate) {
        this.resDate = resDate;
    }

    public String getResCreator() {
        return resCreator;
    }

    public void setResCreator(String resCreator) {
        this.resCreator = resCreator;
    }

    public Long getResSize() {
        return resSize;
    }

    public void setResSize(Long resSize) {
        this.resSize = resSize;
    }
}
