package com.jeeplus.blog.common.service.impl;

import com.jeeplus.blog.common.service.CommonService;
import com.jeeplus.blog.entities.BlogRequestLog;
import com.jeeplus.common.util.TimeStampUtils;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-24 14:35.
 */
@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private EhCacheCacheManager cacheManager;
    @Value("${app.debug}")
    protected boolean debug;//是否开发模式
    @Value("${app.host}")
    protected String host;
    @Value("${app.file.temp.folder}")
    /**
     * 文件上传处理临时目录
     */
    protected String fileTempFolder;

    public boolean isDebug() {
        return debug;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public String getFileTempFolder() {
        return fileTempFolder;
    }

    /**
     * 返回cookie 共享顶级域名
     * @return
     */
    public String getShareCookHost(){
        //如果是本地开发模式，配置的localhost那就不要加点，不然登录会有问题
        if (host.contains("localhost")) {
            return host;
        }
        return String.format(".%s", host);
    }

    /**
     * 指定的缓存容器是否包含指定的key
     *
     * @param cacheName ehcache配置的name
     * @param key       key
     * @return
     * @
     */
    @Override
    public boolean cacheContrainKey(String cacheName, String key) {
        Element et= cacheManager.getCacheManager().getCache(cacheName).get(key);
        return null==et.getObjectValue();
    }

    /**
     * 向指定的缓存容器存放对象
     *
     * @param cacheName
     * @param key
     * @param value
     */
    @Override
    public void addCache(String cacheName, String key, Object value) {

        Element et=new Element(key, value);
        cacheManager.getCacheManager().getCache(cacheName).put(et);
    }

    /**
     * 移除某一项
     *
     * @param cacheName
     * @param key
     */
    @Override
    public void removeCache(String cacheName, String key) {

        cacheManager.getCacheManager().getCache(cacheName).remove(key);

    }

    /**
     * 得到缓存
     *
     * @param cacheName
     * @param key
     * @return
     * @
     */
    @Override
    public Object getCache(String cacheName, String key) {
        Element et= cacheManager.getCacheManager().getCache(cacheName).get(key);
        return null==et||null==et.getObjectValue()?null:et.getObjectValue();
    }

    /**
     * 记录http访问日志
     *
     * @param url
     * @param method
     * @param ip
     * @param agent
     * @param referer
     * @param runmills
     * @param blog
     */
    @Override
    public void addHttpRequestLog(String url, String method, String ip,
                                  String agent, String referer, int runmills, String blog) {

        BlogRequestLog log = new BlogRequestLog();
        log.setReqUrl(url);
        log.setReqMethod(method);
        log.setReqIp(ip);
        log.setReqAgent(agent);
        log.setReqReferer(referer);
        log.setReqRunTime(runmills);
        log.setReqDatetime(TimeStampUtils.getCurrentDate());
        log.setReqBlog(blog);

    }

    /**
     * 得到一个实体
     *
     * @param clazz
     * @param id
     * @return
     * @
     */
    @Override
    public Object get(Class<?> clazz, Object id) {
        return null;
    }
}
