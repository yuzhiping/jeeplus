package com.jeeplus.blog.controller.dto;

import java.sql.Timestamp;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-23 19:55.
 */
public class BlogMsgBoardDTO {

    private String id;
    private String websiteid;
    private String msgText;
    private BlogUserDTO msgSendUser;
    private BlogUserDTO msgAt;
    private String msgRoot;
    private Timestamp msgDatetime;
    private String msgFlag;
    private Integer msgLike;
    private Integer msgCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWebsiteid() {
        return websiteid;
    }

    public void setWebsiteid(String websiteid) {
        this.websiteid = websiteid;
    }

    public String getMsgText() {
        return msgText;
    }

    public void setMsgText(String msgText) {
        this.msgText = msgText;
    }

    public BlogUserDTO getMsgSendUser() {
        return msgSendUser;
    }

    public void setMsgSendUser(BlogUserDTO msgSendUser) {
        this.msgSendUser = msgSendUser;
    }

    public BlogUserDTO getMsgAt() {
        return msgAt;
    }

    public void setMsgAt(BlogUserDTO msgAt) {
        this.msgAt = msgAt;
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

    @Override
    public String toString() {
        return "BlogMsgBoardDTO{" +
                "id='" + id + '\'' +
                ", websiteid='" + websiteid + '\'' +
                ", msgText='" + msgText + '\'' +
                ", msgSendUser=" + msgSendUser +
                ", msgAt=" + msgAt +
                ", msgRoot='" + msgRoot + '\'' +
                ", msgDatetime=" + msgDatetime +
                ", msgFlag='" + msgFlag + '\'' +
                ", msgLike=" + msgLike +
                ", msgCount=" + msgCount +
                '}';
    }
}
