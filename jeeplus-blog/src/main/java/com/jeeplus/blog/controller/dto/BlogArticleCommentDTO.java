package com.jeeplus.blog.controller.dto;

import java.sql.Timestamp;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-23 19:40.
 */
public class BlogArticleCommentDTO {

    private String id;
    private BlogArticleDTO article;
    private String commentText;
    private Timestamp commentDatetime;
    private BlogUserDTO commentUser;
    private String commentWebsiteId;
    private BlogUserDTO commentToSomeone;
    private String commentIsdel;
    private Integer commentSupport;
    private String commentRoot;
    private Integer commentReplyTotal;
    //当前登录用户是否已点赞
    private Boolean existsSupport;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BlogArticleDTO getArticle() {
        return article;
    }

    public void setArticle(BlogArticleDTO article) {
        this.article = article;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Timestamp getCommentDatetime() {
        return commentDatetime;
    }

    public void setCommentDatetime(Timestamp commentDatetime) {
        this.commentDatetime = commentDatetime;
    }

    public BlogUserDTO getCommentUser() {
        return commentUser;
    }

    public void setCommentUser(BlogUserDTO commentUser) {
        this.commentUser = commentUser;
    }

    public String getCommentWebsiteId() {
        return commentWebsiteId;
    }

    public void setCommentWebsiteId(String commentWebsiteId) {
        this.commentWebsiteId = commentWebsiteId;
    }

    public BlogUserDTO getCommentToSomeone() {
        return commentToSomeone;
    }

    public void setCommentToSomeone(BlogUserDTO commentToSomeone) {
        this.commentToSomeone = commentToSomeone;
    }

    public String getCommentIsdel() {
        return commentIsdel;
    }

    public void setCommentIsdel(String commentIsdel) {
        this.commentIsdel = commentIsdel;
    }

    public Integer getCommentSupport() {
        return commentSupport;
    }

    public void setCommentSupport(Integer commentSupport) {
        this.commentSupport = commentSupport;
    }

    public String getCommentRoot() {
        return commentRoot;
    }

    public void setCommentRoot(String commentRoot) {
        this.commentRoot = commentRoot;
    }

    public Integer getCommentReplyTotal() {
        return commentReplyTotal;
    }

    public void setCommentReplyTotal(Integer commentReplyTotal) {
        this.commentReplyTotal = commentReplyTotal;
    }

    public Boolean getExistsSupport() {
        return existsSupport;
    }

    public void setExistsSupport(Boolean existsSupport) {
        this.existsSupport = existsSupport;
    }

    @Override
    public String toString() {
        return "BlogArticleCommentDTO{" +
                "id='" + id + '\'' +
                ", article=" + article +
                ", commentText='" + commentText + '\'' +
                ", commentDatetime=" + commentDatetime +
                ", commentUser=" + commentUser +
                ", commentWebsiteId='" + commentWebsiteId + '\'' +
                ", commentToSomeone=" + commentToSomeone +
                ", commentIsdel='" + commentIsdel + '\'' +
                ", commentSupport=" + commentSupport +
                ", commentRoot='" + commentRoot + '\'' +
                ", commentReplyTotal=" + commentReplyTotal +
                ", existsSupport=" + existsSupport +
                '}';
    }
}
