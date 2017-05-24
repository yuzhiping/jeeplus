package com.jeeplus.blog.web.interceptor;

import com.jeeplus.blog.common.enums.BLOG_MODEL;
import com.jeeplus.blog.common.enums.Variable;
import com.jeeplus.blog.common.service.impl.CommonServiceImpl;
import com.jeeplus.blog.common.skin.template.SkinTemplateVariable;
import com.jeeplus.blog.controller.dto.BlogUserDTO;
import com.jeeplus.blog.entities.BlogUser;
import com.jeeplus.blog.entities.BlogWebSite;
import com.jeeplus.blog.service.BlogArticleService;
import com.jeeplus.blog.service.BlogWebSiteService;
import com.jeeplus.blog.service.system.SysUrlValidationService;
import com.jeeplus.blog.util.BlogUtils;
import com.jeeplus.blog.util.freemarker.TemplateStaticUtils;
import com.jeeplus.common.util.UrlUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 博客系统url映射跳转核心过滤器
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-05-24 11:01.
 */
@Component("BlogFilter")
public class BlogFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(BlogFilter.class);

    @Autowired
    private BlogWebSiteService blogWebSiteService;
    @Autowired
    private BlogArticleService blogArticleService;
    @Resource(name="CommonServiceImpl")
    private CommonServiceImpl commonService;
    @Autowired
    private SysUrlValidationService sysUrlValidationService;
    //默认跳转博客
    @Value("${app.default.blog.keyword}")
    private String defaultBolg;
    @Autowired
    private TemplateStaticUtils tsu;
    //runtime declare
    private String blogname;//当前博客名
    @Value("${sina_weibo_login_url}")
    private String sinaWeiBoLoginUrl;
    @Autowired
    private SkinTemplateVariable skinVariable;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req=(HttpServletRequest) request;
        HttpServletResponse rep=(HttpServletResponse) response;
        String url= req.getRequestURI();
        long start_time=System.currentTimeMillis();
        String referer =req.getHeader("referer");
        String agent=req.getHeader("User-Agent");
        BlogUtils bu =new BlogUtils(req);
        try {
            if (url.matches(".*\\.php")) {
                rep.sendError(403);
                return;
            }else  if (url.matches("^/www/.*")) {
                if (commonService.isDebug()) {
                    log.debug("静态资源直接返回{}",req.getRequestURL());
                }
                filterChain.doFilter(request, response);//静态资源直接返回
                return;
            }else if (StringUtils.isEmpty(agent)) {
                rep.sendError(403);//拒绝非浏览器访问
                return;
            }
            filter(req, rep, request, response, filterChain, url, bu, referer);
            String req_blog=null==blogname?"51so":blogname;
            String req_url= URLDecoder.decode(url, "UTF-8");
            if (rep.getStatus()==HttpServletResponse.SC_NOT_FOUND||rep.getStatus()!=200) {
                log.error(
                        "[{}] url:{} furl:{} referer:{} user-agent:{} ip:{} run {} millis .",
                        rep.getStatus(),
                        req_url,
                        URLDecoder.decode(req.getRequestURL().toString(), "UTF-8"),
                        referer,req.getHeader("User-Agent"),
                        req.getRemoteAddr(),
                        System.currentTimeMillis()-start_time
                );
                return; //不记录不存在的链接
            }
            else if (url.matches("^/[admin|pub|font]+.*")) {
                return ;//不记录后台处理请求
            }
            commonService.addHttpRequestLog(
                    req_url,
                    req.getMethod(),
                    BlogUtils.getClientIP(req),
                    agent, referer,
                    Integer.valueOf(String.valueOf(System.currentTimeMillis()-start_time)),
                    ///admin开头是访问后台页面 不能算入博客名
                    req_url.matches("^/admin/.*")?"51so":req_blog
            );
        } catch (Exception e) {
            log.error("user:{}|agent:{}|referer:{}|ip:{}|method:{}",null==bu.getCurrentUser()?"guest":bu.getCurrentUser().getUserName(),req.getHeader("User-Agent"),referer,BlogUtils.getClientIP(req),req.getMethod());
            log.error(URLDecoder.decode(req.getRequestURL().toString(), "UTF-8"), e);
        }

    }


    public void filter(HttpServletRequest req,HttpServletResponse rep,ServletRequest request, ServletResponse response, FilterChain chain,String url,BlogUtils bu,String referer) throws Exception{
        //处理.do和druid的请求
        if (url.endsWith(".do")||url.matches(".*\\.do;jsessionid=\\w{32}.*")||url.matches("\\/druid.*")) {
            //新浪微博登录
            if (url.matches(".*(sinawblogin).do$")) {
                bu.setSinaLoginReferer(referer);
                rep.sendRedirect(sinaWeiBoLoginUrl);
            }else if (url.matches("^/pub/.*")) {
                chain.doFilter(request, response);
            }else{
                BlogUserDTO u = bu.getCurrentUser();
                if (null==u) {
                    String rurl=String.format("%s://%s%s%s", (commonService.isDebug()?"http":"https"),commonService.getHost(),"/pub/login.do?url=",req.getRequestURL().toString());
                    rep.sendRedirect(rurl);
                    return;
                }else if (url.matches("^/font/.*")) {
                    chain.doFilter(request, response);
                }else if (!u.getUserLevel().equals("admin")) {
                    log.error("拦截用户：{} 请求链接：{}",u.getUserName(),url);
                    rep.sendError(403);
                }else if(null==u.getUserEmailActivate()||!(u.getUserEmailActivate()>0)){
                    req.setAttribute("msg", "系统升级，必须完成邮箱验证！");
                    req.getRequestDispatcher("/admin/profile.do").forward(request, response);
                    return;
                }
                else{
                    req.getSession().setAttribute(Variable.FT_ADMIN_BLOG_PREFIX.getKey(),String.format("http://%s", commonService.getHost()));
                    chain.doFilter(request, response);
                }

            }
        }else{
            blogname=getBlogName(url,req);
            if (null!=blogname&&blogname.equals("www")) {
                if (url.matches("\\/q")) {
                    req.getRequestDispatcher("/q").forward(request, response);
                }else if(url.equals("/")){
                    req.getRequestDispatcher("/websitehome").forward(request, response);
                }else if (url.endsWith("sitemap.xml")) {
                    rep.sendError(403);
                }
                else{
                    chain.doFilter(request, response);
                }
                return;
            }
            BlogWebSite web = null;
            if (null!=blogname){
                web = blogWebSiteService.getWebSite(blogname);
            }
            if (null==web||null==blogname) {
                rep.sendError(404, String.format("博客 %s 不存在 not found ...", blogname));
            }else{
                skinVariable.setVariable(req, web, BLOG_MODEL.FONT_MODEL);
                //博客站点对象
                req.getSession().setAttribute(Variable.SESSION_BLOG_WEBSITE.getKey(), web);
                if (url.matches("(\\/\\w+)?\\/entry/.*")) {
                    //entry 文章跳转 统一用:/entry/\\w{32}.html 访问  但要兼容以前的 /用户名/entry/\\w{32}.html
                    req.getRequestDispatcher(String.format("%s%s%s", "/entry","/",UrlUtils.getLastSlashData(url))).forward(request, response);
                }else if (url.matches("(\\/\\w+)?\\/tags/.*")) {
                    //tags 标签跳转 统一用:/tags/xxx 访问  但要兼容以前的 /用户名/tags/xxx
                    if (!sysUrlValidationService.validateLable(web.getId(),url))rep.sendError(404);
                    req.getRequestDispatcher(String.format("%s%s%s", "/tags","/",UrlUtils.getLastSlashData(url))).forward(request, response);
                }else if (url.matches("(\\/\\w+)?\\/category/.*")) {
                    //category 类型跳转 统一用:/category/xxx 访问  但要兼容以前的 /用户名/category/xxx
                    if (!sysUrlValidationService.validateCategory(web.getId(),url))rep.sendError(404);
                    req.getRequestDispatcher(String.format("%s%s%s", "/category","/",UrlUtils.getLastSlashData(url))).forward(request, response);
                }else if (url.matches("(\\/\\w+)?\\/date/\\d{6,8}")) {
                    //date 日期跳转 统一用:/date/xxx 访问  但要兼容以前的 /用户名/date/xxx
                    req.getRequestDispatcher(String.format("%s%s%s", "/date","/", UrlUtils.getLastSlashData(url))).forward(request, response);
                }else  if (url.endsWith("sitemap.xml")) {
                    sitemap(req, rep, web.getId());
                }else if (url.matches("(\\/\\w+)?\\/article_lis")) {
                    //博文目录
                    req.getRequestDispatcher("/article_lis").forward(request, response);
                }else if (url.matches("(\\/\\w+)?\\/lable_lis")) {
                    //热门标签
                    req.getRequestDispatcher("/lable_lis").forward(request, response);
                }
                else if (url.matches("(\\/\\w+)?\\/bookmark_lis")) {
                    //书签
                    req.getRequestDispatcher("/bookmark_lis").forward(request, response);
                }else if (url.matches("(\\/\\w+)?\\/blog-msgboard")) {
                    //留言板
                    req.getRequestDispatcher("/blog-msgboard").forward(request, response);
                }else if (url.matches("(\\/\\w+)?\\/blog-timeline")) {
                    //时光轴
                    req.getRequestDispatcher("/blog-timeline").forward(request, response);
                }else if (url.matches("(\\/\\w+)?\\/article_timeline")) {
                    //文章归档
                    req.getRequestDispatcher("/article_timeline").forward(request, response);
                }
                else{
                    boolean jump=true;
                    if (Subdomain(req.getRequestURL().toString())) {
                        //如果是子域名
                        if(url.equals("/")){
                            req.getRequestDispatcher("/home").forward(request, response);
                            jump=false;
                        }
                    }else {
                        if(url.matches("\\/\\w*(\\/)?")){
                            req.getRequestDispatcher("/home").forward(request, response);
                            jump=false;
                        }else if (url.matches("\\/\\w*\\/query")) {
                            req.getRequestDispatcher("/query").forward(request, response);
                            jump=false;
                        }
                    }
                    if (jump) {
                        chain.doFilter(request, response);
                    }
                }

            }
        }
    }

    /**
     * 是否是子域名的方式
     * @param url
     * @return
     */
    public boolean Subdomain(String url){
        return url.matches(String.format("http://\\w+.%s.*", commonService.getHost()));
    }
    /**
     * 得到博客的名字
     * @param url
     * @return
     */
    public String getBlogName(String url,HttpServletRequest req){
        String domain=req.getRequestURL().toString();
        if (Subdomain(domain)) {
            String name="";
            Pattern p = Pattern.compile(":\\//\\w+");
            Matcher m = p.matcher(req.getRequestURL());
            while(m.find()) {
                name=m.group().replaceAll("\\W+", "");
            }
            if (name.equals("www")) {
                //兼容http://www.51so.info/博客名/entry/
                if (url.matches("^\\/\\w+\\/(entry|tags|category|date)/.*")) {
                    return url.split("/")[1];
                }
            }
            return name;
        }else if (url.endsWith(".do")||url.matches(".*\\.do;jsessionid=\\w{32}$")) {
            BlogUser u = (BlogUser) req.getSession().getAttribute(BlogUtils.CURRENT_USER);
            return null==u?url.split("/")[1]:u.getUserName();
        }/*else if (commonServ.isDebug()&&domain.matches("^http(s)?://localhost/.*")) {
        	//开发模式下的url映射
        	String keywords[]=domain.replace("//", "").split("/");
        	return keywords[1];
		}*/else{
            String words[]=url.split("/");
            return words.length==0?defaultBolg:words[1];
        }
    }
    public void sitemap(HttpServletRequest req,HttpServletResponse rep,String websiteid) throws Exception{
        long startDate=System.currentTimeMillis();
        Map<String, Object> _data=new HashMap<String, Object>();
        rep.setContentType("text/xml;charSet=UTF-8");
        String templateName="/system/sitemap.html";
        List<?> lis =blogArticleService.getSiteMapByWebSite(websiteid);
        _data.put("data", lis);
        _data.put("host", commonService.getHost());
        _data.put("millisecond", System.currentTimeMillis()-startDate);
        _data.put("blog_url_prefix", String.format("http://%s.%s", blogname,commonService.getHost()));
        tsu.renderTemplate(req, rep, _data, templateName);
    }


    @Override
    public void destroy() {

    }
}
