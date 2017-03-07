package com.jeeplus.weixin.controller;

import com.jeeplus.weixin.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 微信的首页
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2016-10-17 19:34.
 */
@Controller
@RequestMapping("/home")
public class HomeController extends BaseController {


    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String Index(HttpServletRequest request, HttpServletResponse response){

        //在这里需要判断是否是从微信内置的浏览器进入，且判断微信的版本
        if (1==1)
            return "home";

        return "redirect:/index.jsp";
    }


}
