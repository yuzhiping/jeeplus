package com.jeeplus.blog.service.pub.impl;

import com.jeeplus.blog.common.Pagination;
import com.jeeplus.blog.controller.dto.BlogArticleDTO;
import com.jeeplus.blog.service.pub.PubBlogService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-24 14:48.
 */
@Service
public class PubBlogServiceImpl implements PubBlogService {
    /**
     * 博客动态
     *
     * @param limit
     * @param page
     * @param userId
     * @param websiteName
     * @return
     */
    @Override
    public Pagination blogTimeLine(Integer limit, Integer page, String userId, String websiteName) {
        return null;
    }

    /**
     * 留言板
     *
     * @param limit
     * @param page
     * @param website
     * @return
     */
    @Override
    public Pagination blogMsgBoard(Integer limit, Integer page, String website) {
        return null;
    }

    /**
     * 找到相似的文章
     *
     * @param website
     * @param keyword
     * @param limit
     * @return
     */
    @Override
    public List<BlogArticleDTO> similarArticles(String website, String keyword, int limit) {
        return null;
    }
}
