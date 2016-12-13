package com.jeeplus.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统页面视图
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2016-12-12 14:48.
 */
@Controller
public class SysPageController {
    @RequestMapping("sys/{url}.html")
    public String page(@PathVariable("url") String url){
        return "sys/" + url + ".html";
    }
}
