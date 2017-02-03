package com.jeeplus.web.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * 文件上传
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-01-12 18:44.
 */
@Controller
@RequestMapping("/sys/fileupload")
public class FileUploadController {

    @RequestMapping("/list")
    @RequiresPermissions("sys:fileupload:list")
    public void list(){


    }

}
