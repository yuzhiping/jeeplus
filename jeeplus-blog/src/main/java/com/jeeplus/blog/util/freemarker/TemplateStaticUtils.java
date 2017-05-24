package com.jeeplus.blog.util.freemarker;

import com.jeeplus.common.util.PathUtils;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

/**
 * 模板静态化处理
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-05-24 11:28.
 */
@Component
public class TemplateStaticUtils {
    private static Logger log = LoggerFactory.getLogger(TemplateStaticUtils.class);
    @Resource(name="freemarkerConfig")
    private FreeMarkerConfigurer freemarkerCfg;
    /**
     * 是否存在缓存
     * @param htmlname
     * @return
     */
    public boolean existsCache(String htmlname){
        String path=String.format("%s%s", PathUtils.getWebInfPath().concat("/page/cache/"),htmlname);
        File f = new File(path);
        return f.exists();
    }
    /**
     * 删除缓存
     * @param htmlname
     * @return
     */
    public boolean deleteCache(String htmlname){
        String path=String.format("%s%s", PathUtils.getWebInfPath(),htmlname);
        File f = new File(path);
        return f.delete();
    }

    /**
     * 生成静态页面主方法
     *
     * @param data
     *            一个Map的数据结果集
     * @param templatePath
     *            ftl模版路径
     * @param htmlname
     *            生成静态页面的名称
     */
    public Boolean crateHTML(Map<String, Object> data, String templatePath, String htmlname) {
        boolean successed=false;
        // 加载模版
        File htmlFile=null;
        try {
            // 指定模版路径
            Template template = freemarkerCfg.getConfiguration().getTemplate(templatePath,"UTF-8");
            template.setEncoding("UTF-8");// 静态页面路径
            String htmlPath = String.format("%s%s", PathUtils.getWebInfPath().concat("/page/cache/"),htmlname);
            htmlFile = new File(htmlPath);
            Writer out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(htmlFile), "UTF-8"));
            // 处理模版
            template.process(data, out);
            out.flush();
            out.close();
            successed=true;
        } catch (Exception e) {
            log.error("静态化处理操作失败,执行删除："+deleteCache(htmlname));
            e.printStackTrace();
        }
        return successed;
    }
    /**
     * 渲染模板
     * @param req
     * @param data
     * @param templatePath
     * @param rep
     * @throws Exception
     */
    public void renderTemplate(HttpServletRequest req, HttpServletResponse rep, Map<String, Object> data, String templatePath) {
        try {
            rep.setContentType("text/html;charSet=UTF-8");
            Template temp =freemarkerCfg.getConfiguration().getTemplate(templatePath,"UTF-8");
            temp.setEncoding("UTF-8");// 静态页面路径
            Writer out = new OutputStreamWriter(rep.getOutputStream(),"UTF-8");
            temp.process(data, out);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
