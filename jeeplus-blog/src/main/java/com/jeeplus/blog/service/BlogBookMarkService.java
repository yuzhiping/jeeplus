package com.jeeplus.blog.service;

import com.jeeplus.blog.common.Pagination;
import com.jeeplus.blog.entities.BlogBookmarks;
import com.jeeplus.blog.entities.BlogBookmarksFolder;
import com.jeeplus.blog.entities.BlogUser;

import java.util.List;
import java.util.Map;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-23 19:18.
 */
public interface BlogBookMarkService {
    /**
     * 根据书签ID得到书签对象
     * @param id
     * @return
     * @
     */
    BlogBookmarks getBookMark(int id);
    /**
     * 编辑书签分类
     * @param bookmarksFolder
     * @param user
     * @param website
     */
    void editBookMarkFolder(BlogBookmarksFolder bookmarksFolder, BlogUser user, String website);
    /**
     * 编辑书签
     * @param bookmarks
     * @param user
     */
    BlogBookmarks editBookMark(BlogBookmarks bookmarks, BlogUser user);
    /**
     * 删除书签
     * @param ids
     * @
     */
    int delBookmars(Integer[] ids);
    /**
     * 得到所有书签分类包
     * @param websiteid
     * @return
     */
    List<BlogBookmarksFolder> getAllBookMarksFolder(String websiteid);


    /**
     * 书签包保存
     * @param id
     * @param name
     * @param website
     * @param userid
     */
    void saveBookFolder(Integer id, String name, String website, String userid);

    /**
     * 删除书签包
     * @param id
     * @param websiteId
     */
    int delBookFolder(Integer id, String websiteId);

    /**
     * 分页书签
     * @param websiteid
     * @param map
     * @param limit
     * @param page
     * @return
     * @
     */
    Pagination getBookMarks(String websiteid, Map<String, Object> map, int limit, int page);
}
