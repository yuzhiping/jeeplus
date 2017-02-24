package com.jeeplus.blog.service.impl;

import com.jeeplus.blog.common.Pagination;
import com.jeeplus.blog.service.BlogLableService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-24 14:29.
 */
@Service
public class BlogLableServiceImpl implements BlogLableService {
    /**
     * 得到热门的标签
     *
     * @param websiteid
     * @param limit
     * @param orderby   @return
     * @
     */
    @Override
    public List<Map<String, Object>> getHotLableList(String websiteid, int limit, String orderby) {
        return null;
    }

    /**
     * 标签文章明细
     *
     * @param lableName
     * @param websiteid
     * @param limit
     * @param page
     * @param orderby
     * @return
     * @
     */
    @Override
    public Pagination getLableArticleItemList(String lableName, String websiteid, int limit, int page, String orderby) {
        return null;
    }

    /**
     * 删除文单标签
     *
     * @param id
     * @return
     * @
     */
    @Override
    public int delArticleLable(String id) {
        return 0;
    }
}
