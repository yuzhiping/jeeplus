package com.jeeplus.blog.mapper;

import com.jeeplus.blog.common.Pagination;
import com.jeeplus.blog.common.mapper.BaseMapper;
import com.jeeplus.blog.entities.BlogArticle;

import java.util.List;
import java.util.Map;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-24 15:09.
 */
public interface BlogArticleMapper extends BaseMapper<BlogArticle> {

    /**
     * 得到最新的文章
     * @param page
     * @param website
     * @return
     * @
     */
    Pagination getNewArticleByPage(int page, int limit, String website, Map<String, Object> args);
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
     * 全文检索
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
     *  主文检索
     * @param page
     * @param limit
     * @param keyword
     * @param fields
     * @return
     * @
     */
    Pagination getHomeFullTextSearch(int page, int limit, String keyword, String... fields);

    /**
     * 通过ID得到文章
     * @param id
     * @return
     * @
     */
    BlogArticle getArticleByID(String id);

    /**
     * 通过id和网站得到文章
     * @param id
     * @param website
     * @return
     * @
     */
    BlogArticle getArticleByID(String id, String website);

    /**
     * 通过标题得到文章
     * @param keyword
     * @param website
     * @return
     * @
     */
    BlogArticle getArticleByTitleOrLinkURL(String keyword, String website);


    /**
     * 通知标签名字得到文章
     * @param lableName
     * @param website
     * @param page
     * @param limit
     * @return
     * @
     */
    Pagination getArticleByLable(String lableName, String website, int page, int limit);

    /**
     * 得到阅读次数最多的文章
     * @param websiteId
     * @param limit
     * @return
     * @
     */
    List<BlogArticle> getHotArticle(String websiteId, int limit) ;


    /**
     * 得到sitemap
     * @param websiteId
     * @return
     * @
     */
    List<Object[]> getSiteMapByWebSite(String websiteId);

    /**
     * 统计月度写日志情况
     * @param websiteId
     * @param year
     * @param month
     * @return
     * @
     */
    Map<String,Object> getEveryDayArticleInfo(String websiteId, int year, int month);

    /**
     * 删除文章
     * @param ids
     * @
     */
    int delArticles(String[] ids);
    /**
     * 删除自己个人的评论
     * @param id
     * @param userid
     * @return
     * @
     */
    int delSelfComments(String id, String userid)  ;

    /**
     * 修改文章类型
     * @param ids
     * @param typeid
     * @return
     * @
     */
    int updateArticleType(String[] ids, String typeid);


    /**
     * 批量修改博客类型
     * @param ids
     * @param category
     * @param websiteId
     * @return
     * @
     */
    int updateArticlesType(String[] ids, String category, String websiteId);

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
     * 删除评论
     * @param ids
     * @return
     * @
     */
    int delArticleComments(String[] ids) ;

    /**
     * 文章置顶
     * @param ids
     * @param websiteId
     * @return
     * @
     */
    int fixedTop(String[] ids, String websiteId) ;
    /**
     * 取消置顶
     * @param ids
     * @param websiteId
     * @return
     * @
     */
    int cancleFixed(String[] ids, String websiteId) ;

    /**
     * 设置文章封面
     * @param ids
     * @param websiteId
     * @param resKey
     * @return
     * @
     */
    int setCover(String[] ids, String websiteId, String resKey) ;

    /**
     * 文章归档
     * @param page 页码
     * @param limit
     * @param website 网站 
     * @return
     * @
     */
    Pagination articleArchive(int page, int limit, String website);
    
}
