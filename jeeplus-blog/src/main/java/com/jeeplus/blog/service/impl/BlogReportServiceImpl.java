package com.jeeplus.blog.service.impl;

import com.jeeplus.blog.common.Pagination;
import com.jeeplus.blog.controller.dto.BlogWebSiteCountInfoDTO;
import com.jeeplus.blog.service.BlogReportService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-24 14:30.
 */
@Service
public class BlogReportServiceImpl implements BlogReportService {
    /**
     * 网站每天请求数
     *
     * @param limit
     * @param website
     * @return
     * @
     */
    @Override
    public List<Map<String, Object>> dayHttpReq(Integer limit, String website) {
        return null;
    }

    /**
     * 博客访问情况
     *
     * @return
     * @
     */
    @Override
    public List<Map<String, Object>> sumBlogAccess() {
        return null;
    }

    /**
     * 博客代理类型
     *
     * @return
     * @
     */
    @Override
    public List<Map<String, Object>> blogAgent() {
        return null;
    }

    /**
     * 日志发表日统计
     *
     * @param limit
     * @param website
     * @return
     * @
     */
    @Override
    public List<Map<String, Object>> dayOfArticleQuantity(Integer limit, String website) {
        return null;
    }

    /**
     * 网站访问情况
     *
     * @param limit   显示条数
     * @param page    页码
     * @param website 网站
     * @param map     查询参数
     * @return
     * @
     */
    @Override
    public Pagination websiteAccessLog(Integer limit, Integer page, String website, Map<String, Object> map) {
        return null;
    }

    /**
     * 网站日志比重
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> articlePercent() {
        return null;
    }

    /**
     * 博客网站统计
     *
     * @param siteid
     * @return
     * @
     */
    @Override
    public BlogWebSiteCountInfoDTO websiteCountInfo(String siteid) {
        return null;
    }
}
