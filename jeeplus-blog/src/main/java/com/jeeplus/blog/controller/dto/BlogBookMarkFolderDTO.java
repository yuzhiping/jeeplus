package com.jeeplus.blog.controller.dto;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-23 19:50.
 */
public class BlogBookMarkFolderDTO {

    private Integer id;
    private String bookmarksName;
    private String bookmarksRemark;

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

    public String getBookmarksRemark() {
        return bookmarksRemark;
    }

    public void setBookmarksRemark(String bookmarksRemark) {
        this.bookmarksRemark = bookmarksRemark;
    }

    @Override
    public String toString() {
        return "BlogBookMarkFolderDTO{" +
                "id=" + id +
                ", bookmarksName='" + bookmarksName + '\'' +
                ", bookmarksRemark='" + bookmarksRemark + '\'' +
                '}';
    }
}
