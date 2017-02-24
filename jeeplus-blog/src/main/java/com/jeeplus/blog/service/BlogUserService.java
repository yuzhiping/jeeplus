package com.jeeplus.blog.service;

import com.alibaba.fastjson.JSONObject;
import com.jeeplus.blog.controller.dto.BlogUserDTO;
import com.jeeplus.blog.entities.BlogUser;
import weibo4j.http.AccessToken;
import weibo4j.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-24 14:11.
 */
public interface BlogUserService {

    /**
     * 得到用户信息
     * @param keyword
     * @return
     * @
     */
    BlogUser getUser(String keyword);
    /**
     * 得到用户小卡片
     * @param username
     * @return
     * @
     */
    JSONObject getUserInfoCard(String username);
    /**
     * 得到用户信息
     * @param keyword
     * @return
     * @
     */
    BlogUserDTO getUserVoByKeyword(String keyword);
    /**
     * 用户登录
     * @param username
     * @param psw 这里要传入MD5值的密码
     * @param isRemember 是否记住密码
     * @param req
     * @param  rep
     * @return  1正常 -1帐号密码错误 0第一将次进入 -2 验证码错误
     * @
     */
    int userLogin(String username, String psw, boolean isRemember, HttpServletRequest req, HttpServletResponse rep);
    /**
     * 保存用户
     * @param user
     * @
     */
    void saveUser(BlogUserDTO user);

    /**
     * 更改密码
     * @param oldPassword
     * @param newPassword
     * @param userDTO
     * @return
     * @
     */
    int modifyPsw(String oldPassword, String newPassword, BlogUserDTO userDTO) ;

    /**
     * 登录失败次数记录在ehcache中
     * @param username
     * @return
     * @
     */
    int getLoginFailures(String username);

    /**
     * 设置登录失败次数
     * @param username
     * @return
     * @
     */
    int setLoginFailures(String username);


    /**
     * 清空登录失败信息
     * @param username
     * @
     */
    void clearLoginFailures(String username);
    /**
     * 根据email找到用户
     * @param email
     * @return
     * @
     */
    List<BlogUserDTO> getUsersVoByEmail(String email);

    /**
     * 通过邮箱找回密码
     * @param email
     * @return
     * @
     */
    String findPswByEmail(String email);



    /**
     * 是否存在新浪用户
     * @param uid
     * @param login 是否是登录  是就写登录时间
     * @return
     * @
     */
    BlogUser existSinaUser(long uid, boolean login);

    /**
     * 新浪微薄用户同步（存在：更新|不存在新增）
     * @param accessToken
     * @
     */
    BlogUserDTO sinaUserSynchronization(AccessToken accessToken, BlogUser user);

    /**
     * 是否可以找回密码
     * @param uid
     * @param key
     * @return
     * @
     */
    boolean canFindPass(String uid, String key);

    /**
     * 只用于找回密码(重置密码)
     * @param password
     * @param uid
     * @
     */
    BlogUserDTO resetPass(String password, String uid);

    /**
     * 邮件激活
     * @param req
     * @param uid
     * @
     */
    void activeEmail(HttpServletRequest req, String uid);

    /**
     * 更新用户头像
     * @param userid 用户id
     * @param base64 base64 code
     * @return
     * @
     */
    String uploadProfile(String userid, String base64);
    
}
