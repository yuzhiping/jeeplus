package com.jeeplus.blog.entities;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-23 17:04.
 */
public class BlogArticleLikes implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String userId;
    private Timestamp createtime;

    @Override
    public String toString() {
        return "BlogArticleLikes{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", createtime=" + createtime +
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

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }
}
