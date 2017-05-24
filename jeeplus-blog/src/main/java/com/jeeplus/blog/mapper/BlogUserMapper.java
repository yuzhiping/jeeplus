package com.jeeplus.blog.mapper;

import com.jeeplus.blog.entities.BlogUser;

import java.util.List;
import java.util.Map;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-05-24 20:08.
 */
public interface BlogUserMapper {

    /**
     * 得到用户信息
     * @param keyword
     * @return
     * @throws Exception
     */
    BlogUser getUser(String keyword)throws Exception;
    /**
     * 得到用户小卡片
     * @param username
     */
    Map<String, Object> getUserInfoCard(String username)throws Exception;
    /**
     * 保存用户
     * @param user
     * @throws Exception
     */
    void saveUser(BlogUser user)throws Exception;

    /**
     * 更新密码
     * @param password
     * @param userid
     * @return
     * @throws Exception
     */
    int modifyPsw(String password, String userid)throws Exception;

    /**
     * 根据邮箱得到用户
     * @param email
     * @return
     * @throws Exception
     */
    List<BlogUser> getUsersByEmail(String email)throws Exception;

    /**
     * 根据sina uid得到绑定的用户
     * @param uid
     * @return
     * @throws Exception
     */
    BlogUser existsSinaUser(long uid)throws Exception;

    /**
     * 传入多个用户名返回数据库存在的用户
     * @param userNamses
     * @return
     * @throws Exception
     */
    List<BlogUser> getUsersByUserNames(String[] userNamses)throws Exception;

}
