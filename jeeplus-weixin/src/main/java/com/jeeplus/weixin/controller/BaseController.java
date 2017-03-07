package com.jeeplus.weixin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Created by lzh on 2016/4/14.
 */
public class BaseController<T> {
 

	protected HttpServletRequest request; 
	protected HttpServletResponse response ;
    protected HttpSession session ;
  
  
    protected final Logger logger  = LoggerFactory.getLogger("error_stack");

    public boolean checkIsPhone(String userAgent){
        String phoneReg = "\\b(ip(hone|od)|android|opera m(ob|in)i"
                +"|windows (phone|ce)|blackberry"
                +"|s(ymbian|eries60|amsung)|p(laybook|alm|rofile/midp"
                +"|laystation portable)|nokia|fennec|htc[-_]"
                +"|mobile|up.browser|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";
        //移动设备正则匹配：手机端、平板
        Pattern phonePat = Pattern.compile(phoneReg, Pattern.CASE_INSENSITIVE);
        // 匹配
        Matcher matcherPhone = phonePat.matcher(userAgent);
        if(matcherPhone.find()){
            return true;
        } else {
            return false;
        }
    }

//    @ExceptionHandler(Exception.class)
//    public String handleException(Exception ex,HttpServletRequest request){
//        return "redirecct:/";
//    }
// 
 
    
    @ModelAttribute
    public void setReqAndResp(HttpServletRequest request,HttpServletResponse response) {
 
    	this.request=request;
    	this.response=response;
    	this.session=request.getSession(true);
    	
    }
 
	public HttpServletResponse getResponse() {
	 
		return response;
	}

 
	public HttpSession getSession() {
 
		return session;
	
	}
 
	
	public void writeJson(String param) {
		
		response.setContentType("Content-type:application/json;charset=UTF-8");
		try {
			response.getWriter().write(param);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
 
	/**
     * 根据名字获取cookie
     * @param request
     * @param name cookie名字
     * @return
     */
    public  Cookie getCookieByName(HttpServletRequest request,String name){
        Map<String,Cookie> cookieMap = ReadCookieMap(request);
        if(cookieMap.containsKey(name)){
            Cookie cookie = (Cookie)cookieMap.get(name);
            return cookie;
        }else{
            return null;
        }   
    }
      
    /**
     * 读取所有cookie
     * 注意二、从客户端读取Cookie时，包括maxAge在内的其他属性都是不可读的，也不会被提交。浏览器提交Cookie时只会提交name与value属性。maxAge属性只被浏览器用来判断Cookie是否过期
     * @param request
     * @param response
     */
 
    public void showCookies(HttpServletRequest request,HttpServletResponse response ){
         
        Cookie[] cookies = request.getCookies();//这样便可以获取一个cookie数组
        if (null==cookies) {
            System.out.println("没有cookie=========");
        } else {
            for(Cookie cookie : cookies){
                System.out.println("name:"+cookie.getName()+",value:"+ cookie.getValue());
            }
        }
         
    }
    /**
     * 添加cookie
     * @param response
     * @param name
     * @param value
     */
 
    public void addCookie(HttpServletResponse response,String name,String value){
        Cookie cookie = new Cookie(name.trim(), value.trim());
        cookie.setMaxAge(30 * 60);// 设置为30min
        cookie.setPath("/");
        System.out.println("已添加===============");
        response.addCookie(cookie);
    }
      
    /**
     * 将cookie封装到Map里面
     * @param request
     * @return
     */
    private  Map<String,Cookie> ReadCookieMap(HttpServletRequest request){  
        Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
        Cookie[] cookies = request.getCookies();
        if(null!=cookies){
            for(Cookie cookie : cookies){
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }
    /**
     * 修改cookie
     * @param request
     * @param response
     * @param name
     * @param value
     * 注意一、修改、删除Cookie时，新建的Cookie除value、maxAge之外的所有属性，例如name、path、domain等，都要与原Cookie完全一样。否则，浏览器将视为两个不同的Cookie不予覆盖，导致修改、删除失败。
     */
 
    public void editCookie(HttpServletRequest request,HttpServletResponse response,String name,String value){
        Cookie[] cookies = request.getCookies();
        if (null==cookies) {
            System.out.println("没有cookie==============");
        } else {
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(name)){
                    System.out.println("原值为:"+cookie.getValue());
                    cookie.setValue(value);
                    cookie.setPath("/");
                    cookie.setMaxAge(30 * 60);// 设置为30min
                    System.out.println("被修改的cookie名字为:"+cookie.getName()+",新值为:"+cookie.getValue());
                    response.addCookie(cookie);
                    break;
                }
            }
        }
         
    }
    public void delCookie(HttpServletRequest request,HttpServletResponse response,String name){
        Cookie[] cookies = request.getCookies();
        if (null==cookies) {
            System.out.println("没有cookie==============");
        } else {
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(name)){
                    cookie.setValue(null);
                    cookie.setMaxAge(0);// 立即销毁cookie
                    cookie.setPath("/");
                    System.out.println("被删除的cookie名字为:"+cookie.getName());
                    response.addCookie(cookie);
                    break;
                }
            }
        }
    }
    

}

