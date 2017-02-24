package com.jeeplus.blog.service;

import com.jeeplus.blog.common.Pagination;

import java.util.List;
import java.util.Map;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-23 19:27.
 */
public interface BlogLableService {

    /**
     * 得到热门的标签
     * @param websiteid
     * @return
     * @
     */
    List<Map<String, Object>> getHotLableList(String websiteid, int limit, String orderby) ;

    /**
     * 标签文章明细
     * @param lableName
     * @param websiteid
     * @param limit
     * @param page
     * @param orderby
     * @return
     * @
     */
    Pagination getLableArticleItemList(String lableName, String websiteid, int limit, int page, String orderby) ;

    /**
     * 删除文单标签
     * @param id
     * @return
     * @
     */
    int delArticleLable(String id);
    
}
