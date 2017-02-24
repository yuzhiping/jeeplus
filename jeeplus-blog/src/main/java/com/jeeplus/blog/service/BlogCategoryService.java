package com.jeeplus.blog.service;

import com.jeeplus.blog.entities.BlogCategory;
import com.jeeplus.blog.entities.BlogUser;

import java.util.List;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-23 19:23.
 */
public interface BlogCategoryService {

    /**
     * 得到博客类型列表
     * @param websiteid
     * @return
     * @
     */
    List<BlogCategory> getCategoryList(String websiteid);

    /**
     * 统计分类
     * @param websiteid
     * @return
     * @
     */
    List<BlogCategory> countCategory(String websiteid);


    /**
     * 保存category
     * @param blogCategory
     * @param websiteid
     * @param user
     */
    void saveCategory(BlogCategory blogCategory, String websiteid, BlogUser user);


    /**
     * 删除类型
     * @param ids
     * @param websiteId
     * @return
     * @
     */
    int delCategory(String[] ids, String websiteId);
    
}
