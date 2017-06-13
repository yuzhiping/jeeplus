package com.jeeplus.blog.mapper;

import com.jeeplus.blog.common.Pagination;
import com.jeeplus.blog.entities.BlogRes;

import java.util.Map;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-05-24 19:51.
 */
public interface BlogResMapper {

    /**
     * 保存
     * @param po
     * @throws Exception
     */
    void saveRes(BlogRes po)throws Exception;
    /**
     * 得到资源列表
     * @param map
     * @param type
     * @param pageindex
     * @param limit
     * @return
     * @throws Exception
     */
    Pagination getResList(String type, int pageindex, int limit, Map<String, Object> map)throws Exception;

    /**
     * 资源列表管理
     * @param pageindex
     * @param limit
     * @param map
     * @return
     * @throws Exception
     */
    Pagination getResList(int pageindex, int limit, Map<String, Object> map) throws Exception ;



    /**
     * 删除博客资源
     * @param keys
     * @param websiteid
     * @throws Exception
     */
    int delBlogRes(String[] keys, String websiteid)throws Exception;

    /**
     * 资源验证是否可管理
     * @param keys
     * @param websiteid
     * @return
     * @throws Exception
     */
    int auth(String keys[], String websiteid)throws Exception;

    /**
     * 资源重命名
     * @param id
     * @param name
     * @param websiteid
     * @throws Exception
     */
    void rename(String id, String name, String websiteid)throws Exception;

}
