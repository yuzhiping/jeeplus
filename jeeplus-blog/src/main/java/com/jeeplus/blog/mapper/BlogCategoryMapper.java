package com.jeeplus.blog.mapper;

import com.jeeplus.blog.common.mapper.BaseMapper;
import com.jeeplus.blog.entities.BlogCategory;

import java.util.List;
import java.util.Map;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-05-24 19:58.
 */
public interface BlogCategoryMapper extends BaseMapper<BlogCategory> {

    /**
     * 得到博客类型列表
     * @param websiteid
     * @return
     * @throws Exception
     */
    List<BlogCategory> getCategoryList(String websiteid)throws Exception;


    /**
     * 统计分类
     * @param websiteid
     * @return
     * @throws Exception
     */
    List<Map<String,Object>> countCategory(String websiteid)throws Exception;

    /**
     * 验证类型名字是否能用
     * @param websiteid
     * @param name
     * @param id 修改时传入id
     * @return
     * @throws Exception
     */
    boolean uniqueCategoryName(String websiteid, String name, String id)throws Exception;

    /**
     * 保存类型
     * @param po
     * @throws Exception
     */
    void saveCategory(BlogCategory po)throws Exception;

    /**
     * 删除类型
     * @param ids
     * @return
     * @throws Exception
     */
    int delCategory(String[] ids, String websiteId)throws Exception;

    /**
     * get a category by a keyword
     * @param website
     * @param keyword
     * @return
     * @throws Exception
     */
    BlogCategory getCategoryByKeyword(String website,String keyword)throws Exception;

}
