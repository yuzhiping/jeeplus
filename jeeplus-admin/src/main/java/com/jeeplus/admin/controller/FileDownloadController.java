package com.jeeplus.admin.controller;

import com.jeeplus.common.utils.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yuzp17311 on 2016/9/12.
 */
@Controller
@RequestMapping("/download")
public class FileDownloadController {
    /**
     * 文件下载
     *
     * @param response
     */
    @RequestMapping(value = "/fileDownload_servlet")
    public void fileDownload_servlet(HttpServletRequest request, HttpServletResponse response) {
        FileUtils.fileDownload(request,response,"/filesOut/Download/mst.txt");
    }
}
