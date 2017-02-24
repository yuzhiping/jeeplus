package com.jeeplus.blog.entities;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-23 17:16.
 */
public class BlogBookmarksFolder implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String bookmarksName;
    private String websiteid;
    private String bookmarksCreator;
    private String bookmarksRemark;
    private Timestamp bookmarksDatetime;

    @Override
    public String toString() {
        return "BlogBookmarksFolder{" +
                "id=" + id +
                ", bookmarksName='" + bookmarksName + '\'' +
                ", websiteid='" + websiteid + '\'' +
                ", bookmarksCreator='" + bookmarksCreator + '\'' +
                ", bookmarksRemark='" + bookmarksRemark + '\'' +
                ", bookmarksDatetime=" + bookmarksDatetime +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookmarksName() {
        return bookmarksName;
    }

    public void setBookmarksName(String bookmarksName) {
        this.bookmarksName = bookmarksName;
    }

    public String getWebsiteid() {
        return websiteid;
    }

    public void setWebsiteid(String websiteid) {
        this.websiteid = websiteid;
    }

    public String getBookmarksCreator() {
        return bookmarksCreator;
    }

    public void setBookmarksCreator(String bookmarksCreator) {
        this.bookmarksCreator = bookmarksCreator;
    }

    public String getBookmarksRemark() {
        return bookmarksRemark;
    }

    public void setBookmarksRemark(String bookmarksRemark) {
        this.bookmarksRemark = bookmarksRemark;
    }

    public Timestamp getBookmarksDatetime() {
        return bookmarksDatetime;
    }

    public void setBookmarksDatetime(Timestamp bookmarksDatetime) {
        this.bookmarksDatetime = bookmarksDatetime;
    }
}
