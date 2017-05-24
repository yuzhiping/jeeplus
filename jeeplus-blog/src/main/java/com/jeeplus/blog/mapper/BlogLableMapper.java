package com.jeeplus.blog.mapper;

import com.jeeplus.blog.common.Pagination;
import com.jeeplus.blog.common.mapper.BaseMapper;
import com.jeeplus.blog.entities.BlogLable;

import java.util.List;
import java.util.Map;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-05-24 20:02.
 */
public interface BlogLableMapper extends BaseMapper<BlogLable> {

    /**
     * 得到热门的标签
     * @param websiteid
     * @return
     * @throws Exception
     */
    List<Map<String, Object>> getHotLableList(String websiteid, int limit, String orderby)throws Exception;

    /**
     * 标签文章明细
     * @param lableName
     * @param websiteid
     * @param limit
     * @param page
     * @param orderby
     * @return
     * @throws Exception
     */
    Pagination getLableArticleItemList(String lableName, String websiteid, int limit, int page, String orderby) throws Exception;

    /**
     * 删除文章标签
     * @param id
     * @throws Exception
     */
    int delArticleLable(String id)throws Exception;

    /**
     * get label quantity
     * @param websiteId
     * @param labName
     * @return
     * @throws Exception
     */
    int getLableQuantityByName(String websiteId,String labName)throws Exception;

}
