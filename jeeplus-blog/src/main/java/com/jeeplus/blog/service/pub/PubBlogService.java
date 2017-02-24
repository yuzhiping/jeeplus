package com.jeeplus.blog.service.pub;

import com.jeeplus.blog.common.Pagination;
import com.jeeplus.blog.controller.dto.BlogArticleDTO;

import java.util.List;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-24 14:46.
 */
public interface PubBlogService {

    /**
     * 博客动态
     * @param limit
     * @param page
     * @param userId
     * @param websiteName
     * @return
     * 
     */
    Pagination blogTimeLine(Integer limit, Integer page, String userId, String websiteName);

    /**
     * 留言板
     * @param limit
     * @param page
     * @param website
     * @return
     * 
     */
    Pagination blogMsgBoard(Integer limit, Integer page, String website);

    /**
     * 找到相似的文章
     * @param website
     * @param keyword
     * @param limit
     * @return
     * 
     */
    List<BlogArticleDTO> similarArticles(String website, String keyword, int limit);

}
