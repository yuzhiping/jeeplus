package com.jeeplus.blog.entities;


import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-23 16:39.
 */
public class BlogUser implements Serializable {

    private String id;
    private String userName;
    private String userPsw;
    private Boolean userSex;
    private String userScreenName;
    private String userEmail;
    private Short userState;
    private Timestamp userDatetime;
    private Timestamp userLastlogin;
    private Boolean userEmailActivate;
    private Long sinaUid;
    private String sinaToken;
    private Long sinaExpireIn;
    private String sinaProfileUrl;
    private String userLevel;
    private String userProfileImg;

    @Override
    public String toString() {
        return "BlogUser{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", userPsw='" + userPsw + '\'' +
                ", userSex=" + userSex +
                ", userScreenName='" + userScreenName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userState=" + userState +
                ", userDatetime=" + userDatetime +
                ", userLastlogin=" + userLastlogin +
                ", userEmailActivate=" + userEmailActivate +
                ", sinaUid=" + sinaUid +
                ", sinaToken='" + sinaToken + '\'' +
                ", sinaExpireIn=" + sinaExpireIn +
                ", sinaProfileUrl='" + sinaProfileUrl + '\'' +
                ", userLevel='" + userLevel + '\'' +
                ", userProfileImg='" + userProfileImg + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPsw() {
        return userPsw;
    }

    public void setUserPsw(String userPsw) {
        this.userPsw = userPsw;
    }

    public Boolean getUserSex() {
        return userSex;
    }

    public void setUserSex(Boolean userSex) {
        this.userSex = userSex;
    }

    public String getUserScreenName() {
        return userScreenName;
    }

    public void setUserScreenName(String userScreenName) {
        this.userScreenName = userScreenName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Short getUserState() {
        return userState;
    }

    public void setUserState(Short userState) {
        this.userState = userState;
    }

    public Timestamp getUserDatetime() {
        return userDatetime;
    }

    public void setUserDatetime(Timestamp userDatetime) {
        this.userDatetime = userDatetime;
    }

    public Timestamp getUserLastlogin() {
        return userLastlogin;
    }

    public void setUserLastlogin(Timestamp userLastlogin) {
        this.userLastlogin = userLastlogin;
    }

    public Boolean getUserEmailActivate() {
        return userEmailActivate;
    }

    public void setUserEmailActivate(Boolean userEmailActivate) {
        this.userEmailActivate = userEmailActivate;
    }

    public Long getSinaUid() {
        return sinaUid;
    }

    public void setSinaUid(Long sinaUid) {
        this.sinaUid = sinaUid;
    }

    public String getSinaToken() {
        return sinaToken;
    }

    public void setSinaToken(String sinaToken) {
        this.sinaToken = sinaToken;
    }

    public Long getSinaExpireIn() {
        return sinaExpireIn;
    }

    public void setSinaExpireIn(Long sinaExpireIn) {
        this.sinaExpireIn = sinaExpireIn;
    }

    public String getSinaProfileUrl() {
        return sinaProfileUrl;
    }

    public void setSinaProfileUrl(String sinaProfileUrl) {
        this.sinaProfileUrl = sinaProfileUrl;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public String getUserProfileImg() {
        return userProfileImg;
    }

    public void setUserProfileImg(String userProfileImg) {
        this.userProfileImg = userProfileImg;
    }

}
