package com.jeeplus.blog.web.interceptor.assist;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-05-24 19:41.
 */
public class BlogUrlMapping {

    private static Map<String, String> mapping;
    static{
        mapping =new TreeMap<String, String>();
        mapping.put("(\\/\\w+)?\\/entry/.*", "/entry/%s");//博文
        mapping.put("(\\/\\w+)?\\/tags/.*", "/tag/%s");//标签
        mapping.put("(\\/\\w+)?\\/category/.*", "/category/%s");//文章类型
        mapping.put("(\\/\\w+)?\\/date/\\d{6,8}", "/date/%s");//日期
    }

}
