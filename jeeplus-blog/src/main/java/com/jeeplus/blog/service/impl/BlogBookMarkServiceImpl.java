package com.jeeplus.blog.service.impl;

import com.jeeplus.blog.common.Pagination;
import com.jeeplus.blog.entities.BlogBookmarks;
import com.jeeplus.blog.entities.BlogBookmarksFolder;
import com.jeeplus.blog.entities.BlogUser;
import com.jeeplus.blog.service.BlogBookMarkService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-24 14:26.
 */
@Service
public class BlogBookMarkServiceImpl implements BlogBookMarkService {
    /**
     * 根据书签ID得到书签对象
     *
     * @param id
     * @return
     * @
     */
    @Override
    public BlogBookmarks getBookMark(int id) {
        return null;
    }

    /**
     * 编辑书签分类
     *
     * @param bookmarksFolder
     * @param user
     * @param website
     */
    @Override
    public void editBookMarkFolder(BlogBookmarksFolder bookmarksFolder, BlogUser user, String website) {

    }

    /**
     * 编辑书签
     *
     * @param bookmarks
     * @param user
     */
    @Override
    public BlogBookmarks editBookMark(BlogBookmarks bookmarks, BlogUser user) {
        return null;
    }

    /**
     * 删除书签
     *
     * @param ids
     * @
     */
    @Override
    public int delBookmars(Integer[] ids) {
        return 0;
    }

    /**
     * 得到所有书签分类包
     *
     * @param websiteid
     * @return
     */
    @Override
    public List<BlogBookmarksFolder> getAllBookMarksFolder(String websiteid) {
        return null;
    }

    /**
     * 书签包保存
     *
     * @param id
     * @param name
     * @param website
     * @param userid
     */
    @Override
    public void saveBookFolder(Integer id, String name, String website, String userid) {

    }

    /**
     * 删除书签包
     *
     * @param id
     * @param websiteId
     */
    @Override
    public int delBookFolder(Integer id, String websiteId) {
        return 0;
    }

    /**
     * 分页书签
     *
     * @param websiteid
     * @param map
     * @param limit
     * @param page
     * @return
     * @
     */
    @Override
    public Pagination getBookMarks(String websiteid, Map<String, Object> map, int limit, int page) {
        return null;
    }
}
