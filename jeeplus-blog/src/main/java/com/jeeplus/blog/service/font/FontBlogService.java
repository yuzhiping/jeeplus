package com.jeeplus.blog.service.font;

import com.alibaba.fastjson.JSONArray;
import com.jeeplus.blog.controller.dto.BlogArticleCommentDTO;

import java.util.List;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-24 14:55.
 */
public interface FontBlogService {

    /**
     * 留言
     * @param root
     * @param at
     * @param content
     * @param currentUser
     * @param website
     * @param url
     * @
     */
    void addMsgBoard(String root, String at, String content, String currentUser, String website, String url);



    /**
     * 加载更多留言板回复
     * @param id
     * @param page
     * @return
     * @
     */
    JSONArray loadMsgBoardReply(String website, String id, Integer page);

    /**
     * 加载更多文章回复
     * @param articleId
     * @param root
     * @param currentUser
     * @param page
     * @return
     * @
     */
    List<BlogArticleCommentDTO> loadArticleDiscussReply(String articleId, String root, String currentUser, Integer page);

}
