package com.jeeplus.blog.web.servlet;

import com.baidu.ueditor.ActionEnter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-05-24 10:09.
 */
@Component("UploadImgServlet")
public class UploadImgServlet implements HttpRequestHandler {

    Logger logger= LoggerFactory.getLogger(UploadImgServlet.class);

    /**
     * 生成图片的存放路径
     * @param pathPrefix
     * @return
     */
    private String generateImgSavePath(String pathPrefix){

        Calendar calendar=Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day=calendar.get(Calendar.DATE);
        String path;
        boolean windows=System.getProperties().get("os.name").toString().toLowerCase().startsWith("win");
        if(windows){
            path=String.format("%s\\%s\\%s\\%s",pathPrefix, year,month,day);
        }else{
            path=String.format("%s/%s/%s/%s",pathPrefix,year,month,day);
        }
        return path;
    }

    /**
     * 图片url地址
     * @param fileName
     * @return
     */
    private String getImgUrl(String fileName){
        Calendar calendar = Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH)+1;
        int day=calendar.get(Calendar.DATE);
        return String.format("%s/%s/%s/%s",year,month,day,fileName);
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding( "utf-8" );
        response.setHeader("Content-Type" , "text/html");
        String rootPath =request.getSession().getServletContext().getRealPath("/");
        logger.info("请求的根路径为: "+ rootPath);
        String finalStr=new ActionEnter( request, rootPath ).exec();
        PrintWriter out = response.getWriter();
        out.print(finalStr);  //返回url地址
        out.flush();
        out.close();
    }
}
