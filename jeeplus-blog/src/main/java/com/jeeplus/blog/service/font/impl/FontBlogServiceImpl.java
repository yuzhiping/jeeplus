package com.jeeplus.blog.service.font.impl;

import com.alibaba.fastjson.JSONArray;
import com.jeeplus.blog.controller.dto.BlogArticleCommentDTO;
import com.jeeplus.blog.service.font.FontBlogService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-24 15:00.
 */
@Service
public class FontBlogServiceImpl implements FontBlogService {
    /**
     * 留言
     *
     * @param root
     * @param at
     * @param content
     * @param currentUser
     * @param website
     * @param url
     */
    @Override
    public void addMsgBoard(String root, String at, String content, String currentUser, String website, String url) {

    }

    /**
     * 加载更多留言板回复
     *
     * @param website
     * @param id
     * @param page
     * @return
     * @
     */
    @Override
    public JSONArray loadMsgBoardReply(String website, String id, Integer page) {
        return null;
    }

    /**
     * 加载更多文章回复
     *
     * @param articleId
     * @param root
     * @param currentUser
     * @param page
     * @return
     * @
     */
    @Override
    public List<BlogArticleCommentDTO> loadArticleDiscussReply(String articleId, String root, String currentUser, Integer page) {
        return null;
    }
}
