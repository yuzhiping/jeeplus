package com.jeeplus.blog.controller.dto;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-23 19:38.
 */
public class BlogWebSiteDTO {

    private String id;
    private String websiteName;
    private String websiteTitle;
    private String websiteTagline;
    private String websiteKeyword;
    private String websiteAbout;
    private String websiteAnalyticscode;
    private String websiteDatetime;
    private Boolean websiteAllowcomments;
    private String websiteSkin;
    private BlogUserDTO user;
    private String websiteEditor;
    private String websiteNotice;
    private String websiteFooter;
    private String websiteCover;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWebsiteName() {
        return websiteName;
    }

    public void setWebsiteName(String websiteName) {
        this.websiteName = websiteName;
    }

    public String getWebsiteTitle() {
        return websiteTitle;
    }

    public void setWebsiteTitle(String websiteTitle) {
        this.websiteTitle = websiteTitle;
    }

    public String getWebsiteTagline() {
        return websiteTagline;
    }

    public void setWebsiteTagline(String websiteTagline) {
        this.websiteTagline = websiteTagline;
    }

    public String getWebsiteKeyword() {
        return websiteKeyword;
    }

    public void setWebsiteKeyword(String websiteKeyword) {
        this.websiteKeyword = websiteKeyword;
    }

    public String getWebsiteAbout() {
        return websiteAbout;
    }

    public void setWebsiteAbout(String websiteAbout) {
        this.websiteAbout = websiteAbout;
    }

    public String getWebsiteAnalyticscode() {
        return websiteAnalyticscode;
    }

    public void setWebsiteAnalyticscode(String websiteAnalyticscode) {
        this.websiteAnalyticscode = websiteAnalyticscode;
    }

    public String getWebsiteDatetime() {
        return websiteDatetime;
    }

    public void setWebsiteDatetime(String websiteDatetime) {
        this.websiteDatetime = websiteDatetime;
    }

    public Boolean getWebsiteAllowcomments() {
        return websiteAllowcomments;
    }

    public void setWebsiteAllowcomments(Boolean websiteAllowcomments) {
        this.websiteAllowcomments = websiteAllowcomments;
    }

    public String getWebsiteSkin() {
        return websiteSkin;
    }

    public void setWebsiteSkin(String websiteSkin) {
        this.websiteSkin = websiteSkin;
    }

    public BlogUserDTO getUser() {
        return user;
    }

    public void setUser(BlogUserDTO user) {
        this.user = user;
    }

    public String getWebsiteEditor() {
        return websiteEditor;
    }

    public void setWebsiteEditor(String websiteEditor) {
        this.websiteEditor = websiteEditor;
    }

    public String getWebsiteNotice() {
        return websiteNotice;
    }

    public void setWebsiteNotice(String websiteNotice) {
        this.websiteNotice = websiteNotice;
    }

    public String getWebsiteFooter() {
        return websiteFooter;
    }

    public void setWebsiteFooter(String websiteFooter) {
        this.websiteFooter = websiteFooter;
    }

    public String getWebsiteCover() {
        return websiteCover;
    }

    public void setWebsiteCover(String websiteCover) {
        this.websiteCover = websiteCover;
    }

    @Override
    public String toString() {
        return "BlogWebSiteDTO{" +
                "id='" + id + '\'' +
                ", websiteName='" + websiteName + '\'' +
                ", websiteTitle='" + websiteTitle + '\'' +
                ", websiteTagline='" + websiteTagline + '\'' +
                ", websiteKeyword='" + websiteKeyword + '\'' +
                ", websiteAbout='" + websiteAbout + '\'' +
                ", websiteAnalyticscode='" + websiteAnalyticscode + '\'' +
                ", websiteDatetime='" + websiteDatetime + '\'' +
                ", websiteAllowcomments=" + websiteAllowcomments +
                ", websiteSkin='" + websiteSkin + '\'' +
                ", user=" + user +
                ", websiteEditor='" + websiteEditor + '\'' +
                ", websiteNotice='" + websiteNotice + '\'' +
                ", websiteFooter='" + websiteFooter + '\'' +
                ", websiteCover='" + websiteCover + '\'' +
                '}';
    }
}
