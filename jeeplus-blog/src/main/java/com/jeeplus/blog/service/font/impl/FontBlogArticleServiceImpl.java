package com.jeeplus.blog.service.font.impl;

import com.jeeplus.blog.common.Pagination;
import com.jeeplus.blog.controller.dto.BlogArticleDTO;
import com.jeeplus.blog.entities.BlogArticleComment;
import com.jeeplus.blog.service.font.FontBlogArticleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-24 15:01.
 */
@Service
public class FontBlogArticleServiceImpl implements FontBlogArticleService {
    /**
     * 加载文章评论
     *
     * @param page
     * @param limit
     * @param website
     * @param articleId
     * @param model
     * @param currentUser
     * @return
     * @throws Exception
     */
    @Override
    public Pagination getArticleDiscuss(Integer page, Integer limit, String website, String articleId, String model, String currentUser) {
        return null;
    }

    /**
     * 加载指定文章评论回复列表
     *
     * @param page
     * @param limit
     * @param rootId
     * @param currentUser
     * @return
     * @throws Exception
     */
    @Override
    public List<BlogArticleComment> getArticleDiscussReplyById(Integer page, Integer limit, String rootId, String currentUser) {
        return null;
    }

    /**
     * 文章评论
     *
     * @param userId
     * @param tosomeOne
     * @param articleId
     * @param text
     * @param root
     * @throws Exception
     */
    @Override
    public void addArticleDiscuss(String userId, String tosomeOne, String articleId, String text, String root) {

    }

    /**
     * 得到下一篇文章
     *
     * @param datetime
     * @param website
     * @return
     * @throws Exception
     */
    @Override
    public BlogArticleDTO getNext(String datetime, String website) {
        return null;
    }

    /**
     * 得到上一篇文章
     *
     * @param datetime
     * @param website
     * @return
     * @throws Exception
     */
    @Override
    public BlogArticleDTO getPrevious(String datetime, String website) {
        return null;
    }
}
