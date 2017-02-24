package com.jeeplus.blog.service.impl;

import com.jeeplus.blog.common.Pagination;
import com.jeeplus.blog.controller.dto.BlogArticleDTO;
import com.jeeplus.blog.entities.BlogArticle;
import com.jeeplus.blog.entities.BlogArticleLikes;
import com.jeeplus.blog.entities.BlogUser;
import com.jeeplus.blog.entities.BlogWebSite;
import com.jeeplus.blog.service.BlogArticleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-24 14:23.
 */
@Service
public class BlogArticleServiceImpl implements BlogArticleService {
    /**
     * 得到文章列表
     *
     * @param page
     * @param limit
     * @param website
     * @param args    @return
     * @
     */
    @Override
    public Pagination getArticleList(int page, int limit, String website, Map<String, Object> args) {
        return null;
    }

    /**
     * 主页文章列表
     *
     * @param page
     * @param limit
     * @param args
     * @return
     * @
     */
    @Override
    public Pagination getHomeArticleList(int page, int limit, Map<String, Object> args) {
        return null;
    }

    /**
     * 文章全文检索
     *
     * @param page
     * @param limit
     * @param website
     * @param keyword
     * @param fields
     * @return
     * @
     */
    @Override
    public Pagination getFullTextSearch(int page, int limit, String website, String keyword, String... fields) {
        return null;
    }

    /**
     * 文章全文检索
     *
     * @param page
     * @param limit
     * @param keyword
     * @param fields
     * @return
     * @
     */
    @Override
    public Pagination getHomeFullTextSearch(int page, int limit, String keyword, String... fields) {
        return null;
    }

    /**
     * 得到文章
     *
     * @param keyword
     * @param readonce 是否需要记录阅读次数
     * @param website
     * @return
     * @
     */
    @Override
    public BlogArticleDTO ArticleInfo(String keyword, boolean readonce, String website) {
        return null;
    }

    /**
     * 文章归档时间轴
     *
     * @param websiteId
     * @param page
     * @param limit
     * @param date      yyyy-mm
     * @return
     * @
     */
    @Override
    public Pagination getArticleTimeLine(String websiteId, int page, int limit, String date) {
        return null;
    }

    /**
     * 文章归档时间轴导航  年-月
     *
     * @param websiteId
     * @return
     * @
     */
    @Override
    public List<Map<String, Object>> getArticleTimeNavigate(String websiteId) {
        return null;
    }

    /**
     * 通过标签得到文章列表
     *
     * @param lableName
     * @param website
     * @param page
     * @param limit     @return
     * @
     */
    @Override
    public Pagination getArticleByLable(String lableName, String website, int page, int limit) {
        return null;
    }

    /**
     * 得到阅读次数最多的文章
     *
     * @param websiteid
     * @param limit
     * @return
     * @
     */
    @Override
    public List<BlogArticle> getHotArticle(String websiteid, int limit) {
        return null;
    }

    /**
     * 得到sitemap
     *
     * @param websiteid
     * @return
     * @
     */
    @Override
    public List<Object[]> getSiteMapByWebSite(String websiteid) {
        return null;
    }

    /**
     * 统计月度写日志情况
     *
     * @param websiteid
     * @param year
     * @param month     @return
     * @
     */
    @Override
    public Map<String, Object> getEveryDayArticleInfo(String websiteid, int year, int month) {
        return null;
    }

    /**
     * 统计月度写日志情况（使用缓存）
     *
     * @param key@return
     * @
     */
    @Override
    public Map<String, Object> getEveryDayArticleInfoForCache(String key) {
        return null;
    }

    /**
     * 保存文章
     *
     * @param blogArticle
     * @param type        方式： release 发布  update 更新
     * @param user
     * @param ws
     * @return
     * @
     */
    @Override
    public String saveArticle(BlogArticleDTO blogArticle, String type, BlogUser user, BlogWebSite ws) {
        return null;
    }

    /**
     * 删除文章
     *
     * @param ids
     * @return
     * @
     */
    @Override
    public int delArticles(String[] ids) {
        return 0;
    }

    /**
     * 修改文章类别
     *
     * @param ids
     * @param typeid
     * @return
     * @
     */
    @Override
    public int updateArticleType(String[] ids, String typeid) {
        return 0;
    }

    /**
     * 整个文章重生生成索引
     *
     * @
     */
    @Override
    public void buildIndex() {

    }

    /**
     * 改变文章类型
     *
     * @param ids
     * @param category
     * @param websiteid
     * @return
     * @
     */
    @Override
    public int updateArticlesType(String[] ids, String category, String websiteid) {
        return 0;
    }

    /**
     * 得到文章评论
     *
     * @param page
     * @param limit
     * @param articleId
     * @param args      如果包含CUserId 则只显示此用户所有评论
     * @return
     * @
     */
    @Override
    public Pagination getCommentsByArticle(int page, int limit, String articleId, Map<String, Object> args) {
        return null;
    }

    /**
     * 得到文章评论(包含点赞)
     *
     * @param page
     * @param limit
     * @param articleId
     * @param userid
     * @param model
     * @return
     * @
     */
    @Override
    public Pagination getCommentsByArticle(int page, int limit, String articleId, String userid, String model) {
        return null;
    }

    /**
     * 删除文章评论
     *
     * @param ids
     * @return
     * @
     */
    @Override
    public int delArticleComments(String[] ids) {
        return 0;
    }

    /**
     * 删除自已的评论
     *
     * @param id
     * @param userid
     * @return
     * @
     */
    @Override
    public int delSelfComments(String id, String userid) {
        return 0;
    }

    /**
     * 文章置顶
     *
     * @param ids
     * @param websiteid
     * @return
     * @
     */
    @Override
    public int fixedTop(String[] ids, String websiteid) {
        return 0;
    }

    /**
     * 取消置顶
     *
     * @param ids
     * @param websiteid
     * @return
     * @
     */
    @Override
    public int cancleFixed(String[] ids, String websiteid) {
        return 0;
    }

    /**
     * 设置文章封面
     *
     * @param ids
     * @param websiteid
     * @param resKey
     * @return
     * @
     */
    @Override
    public int setCover(String[] ids, String websiteid, String resKey) {
        return 0;
    }

    /**
     * 点赞取消点赞
     *
     * @param userid
     * @param commentId
     * @return +点赞  -取消点赞
     * @
     */
    @Override
    public String articleCommentSupport(String userid, String commentId) {
        return null;
    }

    /**
     * 喜欢 || 取消喜欢文章
     *
     * @param userid
     * @param articleId
     * @return
     * @
     */
    @Override
    public String articleSupport(String userid, String articleId) {
        return null;
    }

    /**
     * 得到当前用户是否已经点赞过该文章
     *
     * @param userid
     * @param articleId
     * @return
     * @
     */
    @Override
    public BlogArticleLikes getArticleLike(String userid, String articleId) {
        return null;
    }
}
