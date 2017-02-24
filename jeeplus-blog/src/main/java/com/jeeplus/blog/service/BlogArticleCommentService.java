package com.jeeplus.blog.service;

import com.jeeplus.blog.common.Pagination;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-23 19:06.
 */
public interface BlogArticleCommentService {

    Pagination getSupportUsersByCommentId(String id);

}
