package com.jeeplus.blog.controller.dto;

import java.sql.Timestamp;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-23 19:46.
 */
public class BlogLableDTO {

    private String id;
    private BlogWebSiteDTO webSite;
    private BlogArticleDTO article;
    private String lableName;
    private String lableCreator;
    private Timestamp lableDatetime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BlogWebSiteDTO getWebSite() {
        return webSite;
    }

    public void setWebSite(BlogWebSiteDTO webSite) {
        this.webSite = webSite;
    }

    public BlogArticleDTO getArticle() {
        return article;
    }

    public void setArticle(BlogArticleDTO article) {
        this.article = article;
    }

    public String getLableName() {
        return lableName;
    }

    public void setLableName(String lableName) {
        this.lableName = lableName;
    }

    public String getLableCreator() {
        return lableCreator;
    }

    public void setLableCreator(String lableCreator) {
        this.lableCreator = lableCreator;
    }

    public Timestamp getLableDatetime() {
        return lableDatetime;
    }

    public void setLableDatetime(Timestamp lableDatetime) {
        this.lableDatetime = lableDatetime;
    }

    @Override
    public String toString() {
        return "BlogLableDTO{" +
                "id='" + id + '\'' +
                ", webSite=" + webSite +
                ", article=" + article +
                ", lableName='" + lableName + '\'' +
                ", lableCreator='" + lableCreator + '\'' +
                ", lableDatetime=" + lableDatetime +
                '}';
    }
}
