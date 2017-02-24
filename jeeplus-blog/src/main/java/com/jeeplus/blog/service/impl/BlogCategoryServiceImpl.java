package com.jeeplus.blog.service.impl;

import com.jeeplus.blog.entities.BlogCategory;
import com.jeeplus.blog.entities.BlogUser;
import com.jeeplus.blog.service.BlogCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-24 14:29.
 */
@Service
public class BlogCategoryServiceImpl implements BlogCategoryService {
    /**
     * 得到博客类型列表
     *
     * @param websiteid
     * @return
     * @
     */
    @Override
    public List<BlogCategory> getCategoryList(String websiteid) {
        return null;
    }

    /**
     * 统计分类
     *
     * @param websiteid
     * @return
     * @
     */
    @Override
    public List<BlogCategory> countCategory(String websiteid) {
        return null;
    }

    /**
     * 保存category
     *
     * @param blogCategory
     * @param websiteid
     * @param user
     */
    @Override
    public void saveCategory(BlogCategory blogCategory, String websiteid, BlogUser user) {

    }

    /**
     * 删除类型
     *
     * @param ids
     * @param websiteId
     * @return
     * @
     */
    @Override
    public int delCategory(String[] ids, String websiteId) {
        return 0;
    }
}
