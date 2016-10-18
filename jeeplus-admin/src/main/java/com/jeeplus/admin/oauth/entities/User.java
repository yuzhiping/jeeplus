package com.jeeplus.admin.oauth.entities;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2016-10-28 18:12.
 */
public class User {


    private Integer id;
    private String username;
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
