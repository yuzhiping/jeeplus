package com.jeeplus.blog.service.font;

import com.jeeplus.blog.common.Pagination;
import com.jeeplus.blog.controller.dto.BlogArticleDTO;
import com.jeeplus.blog.entities.BlogArticleComment;

import java.util.List;

/**
 * 前端博客文章服务接口
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-24 14:56.
 */
public interface FontBlogArticleService {

    /**
     * 加载文章评论
     * @param page
     * @param limit
     * @param website
     * @param articleId
     * @param model
     * @param currentUser
     * @return
     * @throws Exception
     */
    Pagination getArticleDiscuss(Integer page, Integer limit, String website, String articleId, String model, String currentUser);

    /**
     * 加载指定文章评论回复列表
     * @param page
     * @param limit
     * @param rootId
     * @return
     * @throws Exception
     */
    List<BlogArticleComment> getArticleDiscussReplyById(Integer page, Integer limit, String rootId, String currentUser);

    /**
     * 文章评论
     * @param userId
     * @param tosomeOne
     * @param articleId
     * @param text
     * @param root
     * @throws Exception
     */
    void addArticleDiscuss(String userId, String tosomeOne, String articleId, String text, String root);


    /**
     * 得到下一篇文章
     * @param datetime
     * @param website
     * @return
     * @throws Exception
     */
    BlogArticleDTO getNext(String datetime, String website);
    /**
     * 得到上一篇文章
     * @param datetime
     * @param website
     * @return
     * @throws Exception
     */
    BlogArticleDTO getPrevious(String datetime, String website);

}
