package com.jeeplus.blog.entities;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-23 17:18.
 */
public class BlogCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String categoryName;
    private String categoryRemark;
    private String categoryWebsiteid;
    private Integer categoryPosition;
    private Timestamp categoryDatetime;

    @Override
    public String toString() {
        return "BlogCategory{" +
                "id='" + id + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", categoryRemark='" + categoryRemark + '\'' +
                ", categoryWebsiteid='" + categoryWebsiteid + '\'' +
                ", categoryPosition=" + categoryPosition +
                ", categoryDatetime=" + categoryDatetime +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryRemark() {
        return categoryRemark;
    }

    public void setCategoryRemark(String categoryRemark) {
        this.categoryRemark = categoryRemark;
    }

    public String getCategoryWebsiteid() {
        return categoryWebsiteid;
    }

    public void setCategoryWebsiteid(String categoryWebsiteid) {
        this.categoryWebsiteid = categoryWebsiteid;
    }

    public Integer getCategoryPosition() {
        return categoryPosition;
    }

    public void setCategoryPosition(Integer categoryPosition) {
        this.categoryPosition = categoryPosition;
    }

    public Timestamp getCategoryDatetime() {
        return categoryDatetime;
    }

    public void setCategoryDatetime(Timestamp categoryDatetime) {
        this.categoryDatetime = categoryDatetime;
    }
}
