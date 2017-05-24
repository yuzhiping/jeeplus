package com.jeeplus.blog.mapper;

import com.jeeplus.blog.common.Pagination;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-05-24 19:49.
 */
public interface BlogArticleCommentMapper {

    /**
     * 得到点赞人清单
     * @param id
     * @return
     * @throws Exception
     */
    Pagination getSupportUsersByCommentId(String id)throws Exception;

}
