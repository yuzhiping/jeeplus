package com.jeeplus.common.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 文件上传下载的帮助类
 * Created by yuzp17311 on 2016/9/12.
 */
public final class FileUtils {

    private FileUtils(){
        throw new Error("The class can't be intance");
    }

    /**
     * SpringMVC 中的文件上传方法 trasferTo 的方式上传，参数为MultipartFile
     * @param request
     * @param multipartFile
     * @param filePath
     * @return
     */
    public static String fileUpload(HttpServletRequest request, MultipartFile multipartFile,String filePath){
        //时间戳
        String dateString=new SimpleDateFormat("yyyyMMdd hh:mm:ss").format(new Date());
        //服务器路径（绝对路径）
        String savePath=request.getSession().getServletContext().getRealPath(filePath)+ File.separator+dateString;
        File saveDir=new File(savePath);
        if(!saveDir.exists()||!saveDir.isDirectory())
            saveDir.mkdir();
        if(multipartFile!=null){
            //后缀名
            String suffix=multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
            String uuidName= UUID.randomUUID().toString()+suffix;
            //完整文件名
            String fileName=savePath+File.separator+uuidName;
            //相对路径
            String relativePath = filePath + File.separator + dateString + File.separator + uuidName;
            try {
                multipartFile.transferTo(new File(fileName));
                return relativePath;
            }catch (IOException e){
                e.printStackTrace();
                return null;
            }
        }else
            return null;
    }


    /**
     * 文件下载
     * @param request
     * @param response
     * @param filePath
     */
    public static void fileDownload(HttpServletRequest request, HttpServletResponse response,String filePath){
        String realPath=request.getSession().getServletContext().getRealPath(filePath);
        File file=new File(realPath);
        String fileName=file.getName();
        try {
            InputStream inputStream=new BufferedInputStream(new FileInputStream(file));
            byte [] buffer=new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();
            response.reset();
            // 先去掉文件名称中的空格,然后转换编码格式为utf-8,保证不出现乱码,这个文件名称用于浏览器的下载框中自动显示的文件名
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.replaceAll(" ", "").getBytes("utf-8"), "iso8859-1"));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream os = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            os.write(buffer);// 输出文件
            os.flush();
            os.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
