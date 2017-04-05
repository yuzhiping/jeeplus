package com.jeeplus.admin.controller;

import com.jeeplus.admin.common.controller.BaseController;
import com.jeeplus.admin.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 系统后台的登录控制器
 * Created by yuzp17311 on 2016/8/24.
 */
@Controller
@RequestMapping("/sysuser")
public class LoginController extends BaseController {

    @Autowired
    private LoginService loginService;


    /**
     * 系统后台的登录页
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/login.html")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    /**
     * 注册页
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/register.html")
    public ModelAndView register(HttpServletRequest request,HttpServletResponse response){
        ModelAndView modelAndView=new ModelAndView("register");
        return modelAndView;
    }


    /**
     * 验证登陆
     * @param username 登陆用户名
     * @param password 登陆密码
     * @param captchaCode 验证码
     * @return
     */
    @RequestMapping("/login1")
    public String login(String username,String password,String captchaCode){

        return "";

    }

    /**
     * 退出
     * @return
     */
    @RequestMapping("/logout")
    public String logout(){
        return null;
    }

}
