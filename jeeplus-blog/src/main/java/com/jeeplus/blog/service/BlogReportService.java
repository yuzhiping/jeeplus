package com.jeeplus.blog.service;

import com.jeeplus.blog.common.Pagination;
import com.jeeplus.blog.controller.dto.BlogWebSiteCountInfoDTO;

import java.util.List;
import java.util.Map;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-23 19:29.
 */
public interface BlogReportService {

    /**
     * 网站每天请求数
     * @param limit
     * @param website
     * @return
     * @
     */
    List<Map<String, Object>> dayHttpReq(Integer limit, String website);


    /**
     * 博客访问情况
     * @return
     * @
     */
    List<Map<String, Object>> sumBlogAccess();

    /**
     * 博客代理类型
     * @return
     * @
     */
    List<Map<String, Object>> blogAgent();
    /**
     * 日志发表日统计
     * @param limit
     * @param website
     * @return
     * @
     */
    List<Map<String, Object>> dayOfArticleQuantity(Integer limit, String website);


    /**
     * 网站访问情况
     * @param limit 显示条数
     * @param page 页码
     * @param website 网站
     * @param map 查询参数 
     * @return
     * @
     */
    Pagination websiteAccessLog(Integer limit, Integer page, String website, Map<String, Object> map) ;

    /**
     * 网站日志比重
     * @return
     */
    List<Map<String, Object>> articlePercent() ;


    /**
     * 博客网站统计
     * @param siteid
     * @return
     * @
     */
    BlogWebSiteCountInfoDTO websiteCountInfo(String siteid);
    
}
