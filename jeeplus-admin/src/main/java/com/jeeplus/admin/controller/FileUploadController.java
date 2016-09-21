package com.jeeplus.admin.controller;

import com.jeeplus.common.utils.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;

/**
 * Created by yuzp17311 on 2016/9/12.
 */
@Controller
@RequestMapping("/upload")
public class FileUploadController {

    /*
     * 方式一
     * 采用 fileUpload_multipartFile ， file.transferTo 来保存上传的文件
     */
    @RequestMapping(value = "fileUpload_multipartFile")
    @ResponseBody
    public String fileUpload_multipartFile(HttpServletRequest request, @RequestParam("file_upload") MultipartFile multipartFile) {
        //调用保存文件的帮助类进行保存文件，并返回文件的相对路径
        String filePath = FileUtils.fileUpload(request, multipartFile, "/filesOut/Upload");
        return "{\"TFMark\":\"true\",\"Msg\":\"upload success !\",\"filePath\":\"" + filePath + "\"}";
    }

    /*
     * 方式二
     * 采用 fileUpload_multipartRequest file.transferTo 来保存上传文件
     * 参数不写 MultipartFile multipartFile 在request请求里面直接转成multipartRequest，从multipartRequest中获取到文件流
     */
    @RequestMapping(value = "fileUpload_multipartRequest")
    @ResponseBody
    public String fileUpload_multipartRequest(HttpServletRequest request) {
        //将request转成MultipartHttpServletRequest
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        //页面控件的文件流，对应页面控件 input file_upload
        MultipartFile multipartFile = multipartRequest.getFile("file_upload");
        //调用保存文件的帮助类进行保存文件，并返回文件的相对路径
        String filePath = FileUtils.fileUpload(request, multipartFile, "/filesOut/Upload");
        return "{\"TFMark\":\"true\",\"Msg\":\"upload success !\",\"filePath\":\"" + filePath + "\"}";
    }

    /*
     * 方式三
     * 采用 CommonsMultipartResolver file.transferTo 来保存上传文件
     * 自动扫描全部的input表单
     */
    @RequestMapping(value = "fileUpload_CommonsMultipartResolver")
    @ResponseBody
    public String fileUpload_CommonsMultipartResolver(HttpServletRequest request) {
        //将当前上下文初始化给  CommonsMultipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if (multipartResolver.isMultipart(request)) {
            //将request变成多部分request
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            //获取multiRequest 中所有的文件名
            Iterator iter = multipartRequest.getFileNames();
            while (iter.hasNext()) {
                //一次遍历所有文件
                MultipartFile multipartFile = multipartRequest.getFile(iter.next().toString());
                //调用保存文件的帮助类进行保存文件，并返回文件的相对路径
                String fileName = FileUtils.fileUpload(request, multipartFile, "/filesOut/Upload");
            }
        }
        return "upload success ! ";
    }


    /*
     * 多文件上传
     * 采用 MultipartFile[] multipartFile 上传文件方法
     */
    @RequestMapping(value = "fileUpload_spring_list")
    @ResponseBody
    public String fileUpload_spring_list(HttpServletRequest request, @RequestParam("file_upload") MultipartFile[] multipartFile) {
        //判断file数组不能为空并且长度大于0
        if (multipartFile != null && multipartFile.length > 0) {
            //循环获取file数组中得文件
            try {
                for (int i = 0; i < multipartFile.length; i++) {
                    MultipartFile file = multipartFile[i];
                    //保存文件
                    String fileName = FileUtils.fileUpload(request, file, "/filesOut/Upload");
                    System.out.println(fileName);
                }
                return "{\"TFMark\":\"true\",\"Msg\":\"upload success !\"}";
            } catch (Exception ee) {
                return "{\"TFMark\":\"false\",\"Msg\":\"参数传递有误！\"}";
            }
        }
        return "{\"TFMark\":\"false\",\"Msg\":\"参数传递有误！\"}";
    }


}
