package com.jeeplus.blog.common.skin.template;

import com.jeeplus.blog.common.enums.BLOG_MODEL;
import com.jeeplus.blog.common.enums.Variable;
import com.jeeplus.blog.common.service.CommonService;
import com.jeeplus.blog.entities.BlogWebSite;
import com.jeeplus.blog.util.BlogUtils;
import com.jeeplus.common.util.RegexUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 皮肤模板变量
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-05-24 15:16.
 */
@Component
public class SkinTemplateVariable {

    @Autowired
    private CommonService commonService;
    /**
     * 设置模板变量
     * @param req
     * @param site
     */
    public  void setVariable(HttpServletRequest req, BlogWebSite site, BLOG_MODEL model ){
        switch (model) {
            case FONT_MODEL:
                //设置博客URL前缀 ${blogprefix}
                req.getSession().setAttribute(Variable.FT_BLOG_PREFIX.getKey(), getBlogprefix(req,site));
                //设置模板根路径
                req.getSession().setAttribute(Variable.FT_BLOG_TPATH.getKey(),
                        String.format("/skin/%s/font/", site.getWebsiteSkin())
                );
                req.getSession().setAttribute("locationUri",req.getRequestURI());
                BlogUtils bu = new BlogUtils(req);
                String device=null==bu.getUserAgent().getOperatingSystem()?"":String.valueOf(bu.getUserAgent().getOperatingSystem().getDeviceType().getName());
                //设置访问设备
                req.setAttribute("device",device);
                break;

            case ADMIN_MODEL:
                req.getSession().setAttribute(Variable.FT_ADMIN_BLOG_PREFIX.getKey(),
                        String.format("http://%s", commonService.getHost())
                );
                break;
        }


    }

    /**
     * 返回前台博客前缀URL
     * @param req
     * @param site
     * @return ${blogprefix}
     */
    public String getBlogprefix(HttpServletRequest req, BlogWebSite site){
        //开发模式下的 localhost/博客名方式
        String url=req.getRequestURL().toString();
        if (url.matches("^http(s)?://localhost/.*")) {
            return RegexUtils.findStrByRegx(url, "^http(s)?://localhost/\\w+");
        }
        return String.format("http://%s.%s", site.getWebsiteName(),commonService.getHost());

    }

}
