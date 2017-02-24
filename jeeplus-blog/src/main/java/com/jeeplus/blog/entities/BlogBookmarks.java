package com.jeeplus.blog.entities;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-23 17:14.
 */
public class BlogBookmarks implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String bookmarksName;
    private String bookmarksHref;
    private Integer bookmarksOrder;
    private String bookmarksCreator;
    private Timestamp bookmarksDatetime;

    @Override
    public String toString() {
        return "BlogBookmarks{" +
                "id=" + id +
                ", bookmarksName='" + bookmarksName + '\'' +
                ", bookmarksHref='" + bookmarksHref + '\'' +
                ", bookmarksOrder=" + bookmarksOrder +
                ", bookmarksCreator='" + bookmarksCreator + '\'' +
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

    public String getBookmarksHref() {
        return bookmarksHref;
    }

    public void setBookmarksHref(String bookmarksHref) {
        this.bookmarksHref = bookmarksHref;
    }

    public Integer getBookmarksOrder() {
        return bookmarksOrder;
    }

    public void setBookmarksOrder(Integer bookmarksOrder) {
        this.bookmarksOrder = bookmarksOrder;
    }

    public String getBookmarksCreator() {
        return bookmarksCreator;
    }

    public void setBookmarksCreator(String bookmarksCreator) {
        this.bookmarksCreator = bookmarksCreator;
    }

    public Timestamp getBookmarksDatetime() {
        return bookmarksDatetime;
    }

    public void setBookmarksDatetime(Timestamp bookmarksDatetime) {
        this.bookmarksDatetime = bookmarksDatetime;
    }
}
