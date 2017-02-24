package com.jeeplus.blog.entities;


import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-23 16:33.
 */
public class BlogArticle implements Serializable {

    private String id;
    private String articleTitle;
    private String articleContent;
    private String articleSummary;
    private Timestamp articlePubtime;
    private Timestamp articleUpdatetime;
    private String websiteid;
    private Short articleStatus;
    private Integer articleViewcount;
    private Boolean articlePrivate;
    private String articlePassword;
    private Boolean articleAllowcomments;
    private String articleLinkurl;
    private String articleMd5;
    private Integer articleTotalComment;
    private Integer articleSort;
    private String articleCover;
    private String articleEditor;
    private Integer articleLikes;


    @Override
    public String toString() {
        return "BlogArticle{" +
                "id='" + id + '\'' +
                ", articleTitle='" + articleTitle + '\'' +
                ", articleContent='" + articleContent + '\'' +
                ", articleSummary='" + articleSummary + '\'' +
                ", articlePubtime=" + articlePubtime +
                ", articleUpdatetime=" + articleUpdatetime +
                ", websiteid='" + websiteid + '\'' +
                ", articleStatus=" + articleStatus +
                ", articleViewcount=" + articleViewcount +
                ", articlePrivate=" + articlePrivate +
                ", articlePassword='" + articlePassword + '\'' +
                ", articleAllowcomments=" + articleAllowcomments +
                ", articleLinkurl='" + articleLinkurl + '\'' +
                ", articleMd5='" + articleMd5 + '\'' +
                ", articleTotalComment=" + articleTotalComment +
                ", articleSort=" + articleSort +
                ", articleCover='" + articleCover + '\'' +
                ", articleEditor='" + articleEditor + '\'' +
                ", articleLikes=" + articleLikes +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public String getArticleSummary() {
        return articleSummary;
    }

    public void setArticleSummary(String articleSummary) {
        this.articleSummary = articleSummary;
    }

    public Timestamp getArticlePubtime() {
        return articlePubtime;
    }

    public void setArticlePubtime(Timestamp articlePubtime) {
        this.articlePubtime = articlePubtime;
    }

    public Timestamp getArticleUpdatetime() {
        return articleUpdatetime;
    }

    public void setArticleUpdatetime(Timestamp articleUpdatetime) {
        this.articleUpdatetime = articleUpdatetime;
    }

    public String getWebsiteid() {
        return websiteid;
    }

    public void setWebsiteid(String websiteid) {
        this.websiteid = websiteid;
    }

    public Short getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(Short articleStatus) {
        this.articleStatus = articleStatus;
    }

    public Integer getArticleViewcount() {
        return articleViewcount;
    }

    public void setArticleViewcount(Integer articleViewcount) {
        this.articleViewcount = articleViewcount;
    }

    public Boolean getArticlePrivate() {
        return articlePrivate;
    }

    public void setArticlePrivate(Boolean articlePrivate) {
        this.articlePrivate = articlePrivate;
    }

    public String getArticlePassword() {
        return articlePassword;
    }

    public void setArticlePassword(String articlePassword) {
        this.articlePassword = articlePassword;
    }

    public Boolean getArticleAllowcomments() {
        return articleAllowcomments;
    }

    public void setArticleAllowcomments(Boolean articleAllowcomments) {
        this.articleAllowcomments = articleAllowcomments;
    }

    public String getArticleLinkurl() {
        return articleLinkurl;
    }

    public void setArticleLinkurl(String articleLinkurl) {
        this.articleLinkurl = articleLinkurl;
    }

    public String getArticleMd5() {
        return articleMd5;
    }

    public void setArticleMd5(String articleMd5) {
        this.articleMd5 = articleMd5;
    }

    public Integer getArticleTotalComment() {
        return articleTotalComment;
    }

    public void setArticleTotalComment(Integer articleTotalComment) {
        this.articleTotalComment = articleTotalComment;
    }

    public Integer getArticleSort() {
        return articleSort;
    }

    public void setArticleSort(Integer articleSort) {
        this.articleSort = articleSort;
    }

    public String getArticleCover() {
        return articleCover;
    }

    public void setArticleCover(String articleCover) {
        this.articleCover = articleCover;
    }

    public String getArticleEditor() {
        return articleEditor;
    }

    public void setArticleEditor(String articleEditor) {
        this.articleEditor = articleEditor;
    }

    public Integer getArticleLikes() {
        return articleLikes;
    }

    public void setArticleLikes(Integer articleLikes) {
        this.articleLikes = articleLikes;
    }
}
