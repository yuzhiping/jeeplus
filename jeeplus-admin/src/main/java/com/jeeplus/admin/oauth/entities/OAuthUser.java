package com.jeeplus.admin.oauth.entities;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2016-10-28 18:13.
 */
public class OAuthUser {

    private User user;

    private String oAuthType;
    private String oAuthId;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getoAuthType() {
        return oAuthType;
    }

    public void setoAuthType(String oAuthType) {
        this.oAuthType = oAuthType;
    }

    public String getoAuthId() {
        return oAuthId;
    }

    public void setoAuthId(String oAuthId) {
        this.oAuthId = oAuthId;
    }
}
