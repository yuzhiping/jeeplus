package com.jeeplus.blog.entities;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-23 17:09.
 */
public class BlogMsgBoard implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String websiteId;
    private String msgText;
    private String msgRoot;
    private Timestamp msgDatetime;
    private String msgFlag;
    private Integer msgLike;
    private Integer msgCount;

    @Override
    public String toString() {
        return "BlogMsgBoard{" +
                "id='" + id + '\'' +
                ", websiteId='" + websiteId + '\'' +
                ", msgText='" + msgText + '\'' +
                ", msgRoot='" + msgRoot + '\'' +
                ", msgDatetime=" + msgDatetime +
                ", msgFlag='" + msgFlag + '\'' +
                ", msgLike=" + msgLike +
                ", msgCount=" + msgCount +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWebsiteId() {
        return websiteId;
    }

    public void setWebsiteId(String websiteId) {
        this.websiteId = websiteId;
    }

    public String getMsgText() {
        return msgText;
    }

    public void setMsgText(String msgText) {
        this.msgText = msgText;
    }

    public String getMsgRoot() {
        return msgRoot;
    }

    public void setMsgRoot(String msgRoot) {
        this.msgRoot = msgRoot;
    }

    public Timestamp getMsgDatetime() {
        return msgDatetime;
    }

    public void setMsgDatetime(Timestamp msgDatetime) {
        this.msgDatetime = msgDatetime;
    }

    public String getMsgFlag() {
        return msgFlag;
    }

    public void setMsgFlag(String msgFlag) {
        this.msgFlag = msgFlag;
    }

    public Integer getMsgLike() {
        return msgLike;
    }

    public void setMsgLike(Integer msgLike) {
        this.msgLike = msgLike;
    }

    public Integer getMsgCount() {
        return msgCount;
    }

    public void setMsgCount(Integer msgCount) {
        this.msgCount = msgCount;
    }
}
