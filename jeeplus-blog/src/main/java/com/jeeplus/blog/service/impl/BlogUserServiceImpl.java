package com.jeeplus.blog.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jeeplus.blog.controller.dto.BlogUserDTO;
import com.jeeplus.blog.entities.BlogUser;
import com.jeeplus.blog.service.BlogUserService;
import org.springframework.stereotype.Service;
import weibo4j.http.AccessToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-24 14:32.
 */
@Service
public class BlogUserServiceImpl implements BlogUserService {
    /**
     * 得到用户信息
     *
     * @param keyword
     * @return
     * @
     */
    @Override
    public BlogUser getUser(String keyword) {
        return null;
    }

    /**
     * 得到用户小卡片
     *
     * @param username
     * @return
     * @
     */
    @Override
    public JSONObject getUserInfoCard(String username) {
        return null;
    }

    /**
     * 得到用户信息
     *
     * @param keyword
     * @return
     * @
     */
    @Override
    public BlogUserDTO getUserVoByKeyword(String keyword) {
        return null;
    }

    /**
     * 用户登录
     *
     * @param username
     * @param psw        这里要传入MD5值的密码
     * @param isRemember 是否记住密码
     * @param req
     * @param rep
     * @return 1正常 -1帐号密码错误 0第一将次进入 -2 验证码错误
     * @
     */
    @Override
    public int userLogin(String username, String psw, boolean isRemember, HttpServletRequest req, HttpServletResponse rep) {
        return 0;
    }

    /**
     * 保存用户
     *
     * @param user
     * @
     */
    @Override
    public void saveUser(BlogUserDTO user) {

    }

    /**
     * 更改密码
     *
     * @param oldPassword
     * @param newPassword
     * @param userDTO
     * @return
     * @
     */
    @Override
    public int modifyPsw(String oldPassword, String newPassword, BlogUserDTO userDTO) {
        return 0;
    }

    /**
     * 登录失败次数记录在ehcache中
     *
     * @param username
     * @return
     * @
     */
    @Override
    public int getLoginFailures(String username) {
        return 0;
    }

    /**
     * 设置登录失败次数
     *
     * @param username
     * @return
     * @
     */
    @Override
    public int setLoginFailures(String username) {
        return 0;
    }

    /**
     * 清空登录失败信息
     *
     * @param username
     * @
     */
    @Override
    public void clearLoginFailures(String username) {

    }

    /**
     * 根据email找到用户
     *
     * @param email
     * @return
     * @
     */
    @Override
    public List<BlogUserDTO> getUsersVoByEmail(String email) {
        return null;
    }

    /**
     * 通过邮箱找回密码
     *
     * @param email
     * @return
     * @
     */
    @Override
    public String findPswByEmail(String email) {
        return null;
    }

    /**
     * 是否存在新浪用户
     *
     * @param uid
     * @param login 是否是登录  是就写登录时间
     * @return
     * @
     */
    @Override
    public BlogUser existSinaUser(long uid, boolean login) {
        return null;
    }

    /**
     * 新浪微薄用户同步（存在：更新|不存在新增）
     *
     * @param accessToken
     * @param user
     * @
     */
    @Override
    public BlogUserDTO sinaUserSynchronization(AccessToken accessToken, BlogUser user) {
        return null;
    }

    /**
     * 是否可以找回密码
     *
     * @param uid
     * @param key
     * @return
     * @
     */
    @Override
    public boolean canFindPass(String uid, String key) {
        return false;
    }

    /**
     * 只用于找回密码(重置密码)
     *
     * @param password
     * @param uid
     */
    @Override
    public BlogUserDTO resetPass(String password, String uid) {
        return null;
    }

    /**
     * 邮件激活
     *
     * @param req
     * @param uid
     */
    @Override
    public void activeEmail(HttpServletRequest req, String uid) {

    }

    /**
     * 更新用户头像
     *
     * @param userid 用户id
     * @param base64 base64 code
     * @return
     * @
     */
    @Override
    public String uploadProfile(String userid, String base64) {
        return null;
    }
}
