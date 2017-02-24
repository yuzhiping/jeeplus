package com.jeeplus.blog.entities;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-23 17:21.
 */
public class BlogRequestLog implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String reqUrl;
    private String reqMethod;
    private String reqIp;
    private String reqAgent;
    private String reqReferer;
    private Integer reqRunTime;
    private Timestamp reqDatetime;
    private String reqBlog;

    @Override
    public String toString() {
        return "BlogRequestLog{" +
                "id='" + id + '\'' +
                ", reqUrl='" + reqUrl + '\'' +
                ", reqMethod='" + reqMethod + '\'' +
                ", reqIp='" + reqIp + '\'' +
                ", reqAgent='" + reqAgent + '\'' +
                ", reqReferer='" + reqReferer + '\'' +
                ", reqRunTime=" + reqRunTime +
                ", reqDatetime=" + reqDatetime +
                ", reqBlog='" + reqBlog + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReqUrl() {
        return reqUrl;
    }

    public void setReqUrl(String reqUrl) {
        this.reqUrl = reqUrl;
    }

    public String getReqMethod() {
        return reqMethod;
    }

    public void setReqMethod(String reqMethod) {
        this.reqMethod = reqMethod;
    }

    public String getReqIp() {
        return reqIp;
    }

    public void setReqIp(String reqIp) {
        this.reqIp = reqIp;
    }

    public String getReqAgent() {
        return reqAgent;
    }

    public void setReqAgent(String reqAgent) {
        this.reqAgent = reqAgent;
    }

    public String getReqReferer() {
        return reqReferer;
    }

    public void setReqReferer(String reqReferer) {
        this.reqReferer = reqReferer;
    }

    public Integer getReqRunTime() {
        return reqRunTime;
    }

    public void setReqRunTime(Integer reqRunTime) {
        this.reqRunTime = reqRunTime;
    }

    public Timestamp getReqDatetime() {
        return reqDatetime;
    }

    public void setReqDatetime(Timestamp reqDatetime) {
        this.reqDatetime = reqDatetime;
    }

    public String getReqBlog() {
        return reqBlog;
    }

    public void setReqBlog(String reqBlog) {
        this.reqBlog = reqBlog;
    }
}
