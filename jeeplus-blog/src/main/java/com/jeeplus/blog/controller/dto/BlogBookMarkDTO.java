package com.jeeplus.blog.controller.dto;

import com.jeeplus.common.util.RegexUtils;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-23 19:52.
 */
public class BlogBookMarkDTO {

    private Integer id;
    private Integer bfid;
    private String bookmarksName;
    private String bookmarksHref;
    private Integer bookmarksOrder;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBfid() {
        return bfid;
    }

    public void setBfid(Integer bfid) {
        this.bfid = bfid;
    }

    public String getBookmarksName() {
        return bookmarksName;
    }

    public void setBookmarksName(String bookmarksName) {
        //去掉html标签
        if (null!=bookmarksName) {
            this.bookmarksName = RegexUtils.replaceAllHtmlTag(bookmarksName);
        }
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

    @Override
    public String toString() {
        return "BlogBookMarkDTO{" +
                "id=" + id +
                ", bfid=" + bfid +
                ", bookmarksName='" + bookmarksName + '\'' +
                ", bookmarksHref='" + bookmarksHref + '\'' +
                ", bookmarksOrder=" + bookmarksOrder +
                '}';
    }
}
