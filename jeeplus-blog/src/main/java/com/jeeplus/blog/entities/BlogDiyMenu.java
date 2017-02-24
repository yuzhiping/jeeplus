package com.jeeplus.blog.entities;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-23 17:06.
 */
public class BlogDiyMenu implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String menuParent;
    private String menuTitle;
    private String menuHref;
    private String menuTarget;
    private String menuRemark;
    private Timestamp menuDatetime;

    @Override
    public String toString() {
        return "BlogDiyMenu{" +
                "id='" + id + '\'' +
                ", menuParent='" + menuParent + '\'' +
                ", menuTitle='" + menuTitle + '\'' +
                ", menuHref='" + menuHref + '\'' +
                ", menuTarget='" + menuTarget + '\'' +
                ", menuRemark='" + menuRemark + '\'' +
                ", menuDatetime=" + menuDatetime +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMenuParent() {
        return menuParent;
    }

    public void setMenuParent(String menuParent) {
        this.menuParent = menuParent;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public String getMenuHref() {
        return menuHref;
    }

    public void setMenuHref(String menuHref) {
        this.menuHref = menuHref;
    }

    public String getMenuTarget() {
        return menuTarget;
    }

    public void setMenuTarget(String menuTarget) {
        this.menuTarget = menuTarget;
    }

    public String getMenuRemark() {
        return menuRemark;
    }

    public void setMenuRemark(String menuRemark) {
        this.menuRemark = menuRemark;
    }

    public Timestamp getMenuDatetime() {
        return menuDatetime;
    }

    public void setMenuDatetime(Timestamp menuDatetime) {
        this.menuDatetime = menuDatetime;
    }


}
