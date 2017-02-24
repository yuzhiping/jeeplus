package com.jeeplus.blog.service;

import com.jeeplus.blog.common.Pagination;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 资源处理
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-23 19:16.
 */
public interface BlogResService {

    /**
     * 上传图片到七牛
     * @param filepath
     * @param request
     * @return
     * @
     */
    String uploadFile(String filepath, String filename, String filetype, HttpServletRequest request);

    /**
     * 得到资源列表
     * @param actionCode 图片/附件
     * @param pageindex
     * @param request
     * @return
     * @
     */
    String getResList(int actionCode, int pageindex, HttpServletRequest request);

    /**
     * 资源列表管理
     * @param pageindex
     * @param limit
     * @param map
     * @return
     * @
     */
    Pagination getResList(int pageindex, int limit, Map<String, Object> map);

    /**
     * 删除博客资源
     * @param keys
     * @param website
     * @
     */
    int delBlogRes(String[] keys, String website);
    /**
     * 资源重命名
     * @param id
     * @param name
     * @param websiteid
     * @
     */
    void rename(String id, String name, String websiteid);
    
}
