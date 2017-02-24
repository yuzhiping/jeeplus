package com.jeeplus.blog.controller.dto;

import java.util.HashSet;
import java.util.Set;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-23 19:43.
 */
public class BlogArticleDTO {

    private String id;
    private BlogUserDTO user;
    private BlogCategoryDTO category;
    private String articleTitle;
    private String articleContent;
    private String articleSummary;
    private String articlePubtime;
    private String articleUpdatetime;
    private String websiteid;
    private Short articleStatus;
    private Integer articleViewcount;
    private Boolean articlePrivate;
    private String articlePassword;
    private Boolean articleAllowcomments;
    private String articleLinkurl;
    private Integer articleTotalComment;
    private Integer articleSort;
    private String articleCover;
    private String articleEditor;
    private Integer articleLikes;
    private Set<BlogLableDTO> lables = new HashSet<BlogLableDTO>(0);
    //
    private String articleLableStr;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BlogUserDTO getUser() {
        return user;
    }

    public void setUser(BlogUserDTO user) {
        this.user = user;
    }

    public BlogCategoryDTO getCategory() {
        return category;
    }

    public void setCategory(BlogCategoryDTO category) {
        this.category = category;
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

    public String getArticlePubtime() {
        return articlePubtime;
    }

    public void setArticlePubtime(String articlePubtime) {
        this.articlePubtime = articlePubtime;
    }

    public String getArticleUpdatetime() {
        return articleUpdatetime;
    }

    public void setArticleUpdatetime(String articleUpdatetime) {
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

    public Set<BlogLableDTO> getLables() {
        return lables;
    }

    public void setLables(Set<BlogLableDTO> lables) {
        this.lables = lables;
    }

    public String getArticleLableStr() {
        return articleLableStr;
    }

    public void setArticleLableStr(String articleLableStr) {
        this.articleLableStr = articleLableStr;
    }

    @Override
    public String toString() {
        return "BlogArticleDTO{" +
                "id='" + id + '\'' +
                ", user=" + user +
                ", category=" + category +
                ", articleTitle='" + articleTitle + '\'' +
                ", articleContent='" + articleContent + '\'' +
                ", articleSummary='" + articleSummary + '\'' +
                ", articlePubtime='" + articlePubtime + '\'' +
                ", articleUpdatetime='" + articleUpdatetime + '\'' +
                ", websiteid='" + websiteid + '\'' +
                ", articleStatus=" + articleStatus +
                ", articleViewcount=" + articleViewcount +
                ", articlePrivate=" + articlePrivate +
                ", articlePassword='" + articlePassword + '\'' +
                ", articleAllowcomments=" + articleAllowcomments +
                ", articleLinkurl='" + articleLinkurl + '\'' +
                ", articleTotalComment=" + articleTotalComment +
                ", articleSort=" + articleSort +
                ", articleCover='" + articleCover + '\'' +
                ", articleEditor='" + articleEditor + '\'' +
                ", articleLikes=" + articleLikes +
                ", lables=" + lables +
                ", articleLableStr='" + articleLableStr + '\'' +
                '}';
    }
}
