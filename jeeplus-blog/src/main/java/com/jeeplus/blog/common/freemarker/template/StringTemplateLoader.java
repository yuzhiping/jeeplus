package com.jeeplus.blog.common.freemarker.template;

import freemarker.cache.TemplateLoader;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/**
 * 模板加载器
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-05-24 13:34.
 */
public class StringTemplateLoader implements TemplateLoader {

    private String template;
    public StringTemplateLoader(String template){
        this.template = template;
        if(null==template){
            this.template = "";
        }
    }
    @Override
    public Object findTemplateSource(String s) throws IOException {
        return new StringReader(template);
    }

    @Override
    public long getLastModified(Object templateSource) {
        return 0;
    }

    @Override
    public Reader getReader(Object templateSource, String s) throws IOException {
        return (Reader) templateSource;
    }

    @Override
    public void closeTemplateSource(Object templateSource) throws IOException {
        ((StringReader) templateSource).close();
    }
}
