package com.jeeplus.blog.mapper;

import com.jeeplus.blog.entities.BlogWebSite;

import java.util.List;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-05-24 19:45.
 */
public interface BlogWebSiteMapper {

    /**
     * 得到website对象
     * @param keyword
     * @return
     * @throws Exception
     */
    BlogWebSite getWebSite(String keyword)throws Exception;

    /**
     * 保存
     * @param ws
     * @return
     * @throws Exception
     */
    void saveWebSite(BlogWebSite ws)throws Exception;

    /**
     * 查出所有博客列表
     * @return
     * @throws Exception
     */
    List<BlogWebSite> getWebSiteList()throws Exception;

    /**
     * 分析http agent
     * @throws Exception
     */
    void analysisRequestAgent() throws Exception;

}
