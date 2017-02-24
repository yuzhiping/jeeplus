package com.jeeplus.blog.entities;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-23 16:52.
 */
public class BlogArticleComment implements Serializable {
    private String id;
    private String commentText;
    private Timestamp commentDateTime;
    private String commentWebsiteId;
    private String commentIsDel;
    private Integer commentSupport;
    private String commentRoot;
    private Integer commentReplyTotal;

    @Override
    public String toString() {
        return "BlogArticleComment{" +
                "id='" + id + '\'' +
                ", commentText='" + commentText + '\'' +
                ", commentDateTime=" + commentDateTime +
                ", commentWebsiteId='" + commentWebsiteId + '\'' +
                ", commentIsDel='" + commentIsDel + '\'' +
                ", commentSupport=" + commentSupport +
                ", commentRoot='" + commentRoot + '\'' +
                ", commentReplyTotal=" + commentReplyTotal +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Timestamp getCommentDateTime() {
        return commentDateTime;
    }

    public void setCommentDateTime(Timestamp commentDateTime) {
        this.commentDateTime = commentDateTime;
    }

    public String getCommentWebsiteId() {
        return commentWebsiteId;
    }

    public void setCommentWebsiteId(String commentWebsiteId) {
        this.commentWebsiteId = commentWebsiteId;
    }

    public String getCommentIsDel() {
        return commentIsDel;
    }

    public void setCommentIsDel(String commentIsDel) {
        this.commentIsDel = commentIsDel;
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
}
