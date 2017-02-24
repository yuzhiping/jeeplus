package com.jeeplus.blog.service;

import com.jeeplus.blog.controller.dto.BlogWebSiteDTO;
import com.jeeplus.blog.entities.BlogUser;
import com.jeeplus.blog.entities.BlogWebSite;

import java.util.List;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-24 14:17.
 */
public interface BlogWebSiteService {

    /**
     * 通过关键字得到网站对象
     * @param keyword
     * @return
     * @
     */
    BlogWebSite getWebSite(String keyword);

    /**
     * 保存
     * @param webSiteDTO
     * @param user
     */
    BlogWebSite saveWebSite(BlogWebSiteDTO webSiteDTO, BlogUser user);

    /**
     * 得到博客列表
     * @return
     * @
     */
    List<BlogWebSiteDTO> getWebSiteList();



    /**
     * 分析http agent
     * @
     */
    void analysisRequestAgent();
    
}
