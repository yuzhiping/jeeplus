package com.jeeplus.blog.controller.dto;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-23 19:35.
 */
public class BlogUserDTO {

    private String id;
    private String userName;
    private String userPassword;
    private Integer userSex;
    private String userScreenName;
    private String userEmail;
    private Short userState;
    private String userDatetime;
    private String userLastlogin;
    private Integer userEmailActivate;
    private Long sinaUid;
    private String userProfileImg;
    private String sinaToken;
    private Long sinaExpireIn;
    private String sinaProfileUrl;
    private String userLevel;

    /**
     * 用户URL 新浪微博or 博客地址
     */
    private String extendUserUrl;

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

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
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

    public String getUserDatetime() {
        return userDatetime;
    }

    public void setUserDatetime(String userDatetime) {
        this.userDatetime = userDatetime;
    }

    public String getUserLastlogin() {
        return userLastlogin;
    }

    public void setUserLastlogin(String userLastlogin) {
        this.userLastlogin = userLastlogin;
    }

    public Integer getUserEmailActivate() {
        return userEmailActivate;
    }

    public void setUserEmailActivate(Integer userEmailActivate) {
        this.userEmailActivate = userEmailActivate;
    }

    public Long getSinaUid() {
        return sinaUid;
    }

    public void setSinaUid(Long sinaUid) {
        this.sinaUid = sinaUid;
    }

    public String getUserProfileImg() {
        return userProfileImg;
    }

    public void setUserProfileImg(String userProfileImg) {
        this.userProfileImg = userProfileImg;
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

    public String getExtendUserUrl() {
        return extendUserUrl;
    }

    public void setExtendUserUrl(String extendUserUrl) {
        this.extendUserUrl = extendUserUrl;
    }

    @Override
    public String toString() {
        return "BlogUserDTO{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userSex=" + userSex +
                ", userScreenName='" + userScreenName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userState=" + userState +
                ", userDatetime='" + userDatetime + '\'' +
                ", userLastlogin='" + userLastlogin + '\'' +
                ", userEmailActivate=" + userEmailActivate +
                ", sinaUid=" + sinaUid +
                ", userProfileImg='" + userProfileImg + '\'' +
                ", sinaToken='" + sinaToken + '\'' +
                ", sinaExpireIn=" + sinaExpireIn +
                ", sinaProfileUrl='" + sinaProfileUrl + '\'' +
                ", userLevel='" + userLevel + '\'' +
                ", extendUserUrl='" + extendUserUrl + '\'' +
                '}';
    }
}
