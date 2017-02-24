package com.jeeplus.blog.service.impl;

import com.jeeplus.blog.controller.dto.BlogWebSiteDTO;
import com.jeeplus.blog.entities.BlogUser;
import com.jeeplus.blog.entities.BlogWebSite;
import com.jeeplus.blog.service.BlogWebSiteService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-24 14:33.
 */
@Service
public class BlogWebSiteServiceImpl implements BlogWebSiteService {
    /**
     * 通过关键字得到网站对象
     *
     * @param keyword
     * @return
     * @
     */
    @Override
    public BlogWebSite getWebSite(String keyword) {
        return null;
    }

    /**
     * 保存
     *
     * @param webSiteDTO
     * @param user
     */
    @Override
    public BlogWebSite saveWebSite(BlogWebSiteDTO webSiteDTO, BlogUser user) {
        return null;
    }

    /**
     * 得到博客列表
     *
     * @return
     * @
     */
    @Override
    public List<BlogWebSiteDTO> getWebSiteList() {
        return null;
    }

    /**
     * 分析http agent
     *
     * @
     */
    @Override
    public void analysisRequestAgent() {

    }
}
