package com.jeeplus.blog.service;

import com.jeeplus.blog.common.Pagination;
import com.jeeplus.blog.controller.dto.BlogArticleDTO;
import com.jeeplus.blog.entities.BlogArticle;
import com.jeeplus.blog.entities.BlogArticleLikes;
import com.jeeplus.blog.entities.BlogUser;
import com.jeeplus.blog.entities.BlogWebSite;

import java.util.List;
import java.util.Map;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-23 19:10.
 */
public interface BlogArticleService {


    /**
     * 得到文章列表
     * @param page
     * @param website
     * @param args
     * @return
     * @
     */
    Pagination getArticleList(int page, int limit, String website, Map<String, Object> args);

    /**
     * 主页文章列表
     * @param page
     * @param limit
     * @param args
     * @return
     * @
     */
    Pagination getHomeArticleList(int page, int limit, Map<String, Object> args);


    /**
     * 文章全文检索
     * @param page
     * @param limit
     * @param website
     * @param keyword
     * @param fields
     * @return
     * @
     */
    Pagination getFullTextSearch(int page, int limit, String website, String keyword, String... fields);

    /**
     * 文章全文检索
     * @param page
     * @param limit
     * @param keyword
     * @param fields
     * @return
     * @
     */
    Pagination getHomeFullTextSearch(int page, int limit, String keyword, String... fields);

    /**
     * 得到文章
     * @param keyword
     * @param readonce 是否需要记录阅读次数
     * @return
     * @
     */
    BlogArticleDTO ArticleInfo(String keyword, boolean readonce, String website);



    /**
     * 文章归档时间轴
     * @param websiteId
     * @param page
     * @param limit
     * @param date yyyy-mm
     * @return
     * @
     */
    Pagination getArticleTimeLine(String websiteId, int page, int limit, String date);
    /**
     * 文章归档时间轴导航  年-月
     * @param websiteId
     * @return
     * @
     */
    List<Map<String, Object>> getArticleTimeNavigate(String websiteId);
    /**
     * 通过标签得到文章列表
     * @param lableName
     * @param page
     * @return
     * @
     */
    Pagination getArticleByLable(String lableName, String website, int page, int limit)  ;

    /**
     * 得到阅读次数最多的文章
     * @param websiteid
     * @return
     * @
     */
    List<BlogArticle> getHotArticle(String websiteid, int limit);


    /**
     * 得到sitemap
     * @param websiteid
     * @return
     * @
     */
    List<Object[]> getSiteMapByWebSite(String websiteid);


    /**
     * 统计月度写日志情况
     * @param websiteid
     * @return
     * @
     */
    Map<String,Object> getEveryDayArticleInfo(String websiteid, int year, int month);

    /**
     * 统计月度写日志情况（使用缓存）
     * @param key( websiteid, year, month逗号分隔加起组成key)
     * @return
     * @
     */
    Map<String,Object> getEveryDayArticleInfoForCache(String key);


    /**
     * 保存文章
     * @param blogArticle
     * @param type 方式： release 发布  update 更新
     * @param user
     * @param ws
     * @return
     * @
     */
    String saveArticle(BlogArticleDTO blogArticle, String type, BlogUser user, BlogWebSite ws);

    /**
     * 删除文章
     * @param ids
     * @return
     * @
     */
    int delArticles(String[] ids) ;

    /**
     * 修改文章类别
     * @param ids
     * @param typeid
     * @return
     * @
     */
    int updateArticleType(String[] ids, String typeid) ;

    /**
     * 整个文章重生生成索引
     * @
     */
    void buildIndex();

    /**
     * 改变文章类型
     * @param ids
     * @param category
     * @param websiteid
     * @return
     * @
     */
    int updateArticlesType(String[] ids, String category, String websiteid);

    /**
     * 得到文章评论
     * @param page
     * @param limit
     * @param articleId
     * @param args 如果包含CUserId 则只显示此用户所有评论
     * @return
     * @
     */
    Pagination getCommentsByArticle(int page, int limit, String articleId, Map<String, Object> args);

    /**
     * 得到文章评论(包含点赞)
     * @param page
     * @param limit
     * @param articleId
     * @param userid
     * @param model
     * @return
     * @
     */
    Pagination getCommentsByArticle(int page, int limit, String articleId, String userid, String model);



    /**
     * 删除文章评论
     * @param ids
     * @return
     * @
     */
    int delArticleComments(String[] ids) ;

    /**
     * 删除自已的评论
     * @param id
     * @param userid
     * @return
     * @
     */
    int delSelfComments(String id, String userid) ;



    /**
     * 文章置顶
     * @param ids
     * @param websiteid
     * @return
     * @
     */
    int fixedTop(String[] ids, String websiteid) ;
    /**
     * 取消置顶
     * @param ids
     * @param websiteid
     * @return
     * @
     */
    int cancleFixed(String[] ids, String websiteid) ;


    /**
     * 设置文章封面
     * @param ids
     * @param websiteid
     * @param resKey
     * @return
     * @
     */
    int setCover(String[] ids, String websiteid, String resKey) ;


    /**
     * 点赞取消点赞
     * @param userid
     * @param commentId
     * @return +点赞  -取消点赞
     * @
     */
    String articleCommentSupport(String userid, String commentId);

    /**
     * 喜欢 || 取消喜欢文章
     * @param userid
     * @param articleId
     * @return
     * @
     */
    String articleSupport(String userid, String articleId);


    /**
     * 得到当前用户是否已经点赞过该文章
     * @param userid
     * @param articleId
     * @return
     * @
     */
    BlogArticleLikes getArticleLike(String userid, String articleId);
    
}
