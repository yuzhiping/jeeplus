package com.jeeplus.blog.mapper;

import com.jeeplus.blog.common.Pagination;
import com.jeeplus.blog.entities.BlogBookmarks;
import com.jeeplus.blog.entities.BlogBookmarksFolder;

import java.util.List;
import java.util.Map;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-05-24 19:54.
 */
public interface BlogBookMarksMapper {

    /**
     * 保存书签包
     * @param bf
     * @throws Exception
     */
    void saveBookmarksFolder(BlogBookmarksFolder bf)throws Exception;
    /**
     * 保存书签
     * @param bms
     * @throws Exception
     */
    BlogBookmarks saveBookMark(BlogBookmarks bms)throws Exception;

    /**
     * 删除书签
     * @param ids
     * @throws Exception
     */
    int delBookmars(Integer[] ids)throws Exception;
    /**
     * 得到所有书签分类包
     * @param websiteid
     * @return
     */
    List<BlogBookmarksFolder> getAllBookMarksFolder(String websiteid)throws Exception;
    /**
     * 书签包保存
     * @param bf
     * @throws Exception
     */
    void saveBookFolder(BlogBookmarksFolder bf)throws Exception;

    /**
     * 删除书签
     * @param id
     * @throws Exception
     */
    int delBookFolder(Integer id, String websiteid)throws Exception;
    /**
     * 分页书签
     * @param websiteid
     * @param map
     * @param limit
     * @param page
     * @return
     * @throws Exception
     */
    Pagination getBookMarks(String websiteid, Map<String, Object> map, int limit, int page)throws Exception;

}
