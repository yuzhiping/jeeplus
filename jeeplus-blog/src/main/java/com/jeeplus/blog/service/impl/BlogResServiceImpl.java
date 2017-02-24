package com.jeeplus.blog.service.impl;

import com.jeeplus.blog.common.Pagination;
import com.jeeplus.blog.service.BlogResService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-24 14:31.
 */
@Service
public class BlogResServiceImpl implements BlogResService {
    /**
     * 上传图片到七牛
     *
     * @param filepath
     * @param filename
     * @param filetype
     * @param request  @return
     * @
     */
    @Override
    public String uploadFile(String filepath, String filename, String filetype, HttpServletRequest request) {
        return null;
    }

    /**
     * 得到资源列表
     *
     * @param actionCode 图片/附件
     * @param pageindex
     * @param request
     * @return
     * @
     */
    @Override
    public String getResList(int actionCode, int pageindex, HttpServletRequest request) {
        return null;
    }

    /**
     * 资源列表管理
     *
     * @param pageindex
     * @param limit
     * @param map
     * @return
     * @
     */
    @Override
    public Pagination getResList(int pageindex, int limit, Map<String, Object> map) {
        return null;
    }

    /**
     * 删除博客资源
     *
     * @param keys
     * @param website
     */
    @Override
    public int delBlogRes(String[] keys, String website) {
        return 0;
    }

    /**
     * 资源重命名
     *
     * @param id
     * @param name
     * @param websiteid
     */
    @Override
    public void rename(String id, String name, String websiteid) {

    }
}
