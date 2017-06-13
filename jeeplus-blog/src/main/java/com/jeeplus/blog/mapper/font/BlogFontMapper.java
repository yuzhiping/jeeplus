package com.jeeplus.blog.mapper.font;

import com.jeeplus.blog.common.Pagination;
import com.jeeplus.blog.controller.dto.BlogArticleDTO;
import com.jeeplus.blog.entities.BlogArticleComment;
import com.jeeplus.blog.entities.BlogMsgBoard;

import java.util.List;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-05-24 20:12.
 */
public interface BlogFontMapper {
    /**
     * 留言
     *
     * @param bmb
     * @throws Exception
     */
    void addMsgBoard(BlogMsgBoard bmb) throws Exception;

    /**
     * 加载文章评论
     * @param page
     * @param limit
     * @param articleId
     * @param userid
     * @param root
     * @param model
     * @return
     * @throws Exception
     */
    Pagination getArticleDiscuss(int page, int limit, String articleId, String userid, String root, String model) throws Exception;


    /**
     *  加载指定文章评论回复列表
     * @param page
     * @param limit
     * @param rootId
     * @param currentUser
     * @return
     * @throws Exception
     */
    List<BlogArticleComment> getArticleDiscussReplyById(Integer page, Integer limit, String rootId, String currentUser) throws Exception;



    /**
     * 得到下一篇文章
     * @param datetime
     * @param website
     * @return
     * @throws Exception
     */
    BlogArticleDTO getNext(String datetime, String website)throws Exception;
    /**
     * 得到上一篇文章
     * @param datetime
     * @param website
     * @return
     * @throws Exception
     */
    BlogArticleDTO getPrevious(String datetime, String website)throws Exception;

    /**
     * 得到相似文章
     * @param keyword
     * @return
     * @throws Exception
     */
    List<BlogArticleDTO> similarArticles(String website, String keyword, int limit)throws Exception;
}
