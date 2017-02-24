package com.jeeplus.blog.entities;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-23 17:01.
 */
public class BlogArticleCommentSupport implements Serializable {

    private String id;
    private String userId;
    private Timestamp createTime;

    @Override
    public String toString() {
        return "BlogArticleCommentSupport{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
