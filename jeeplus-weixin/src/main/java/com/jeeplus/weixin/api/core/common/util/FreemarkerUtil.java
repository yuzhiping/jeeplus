package com.jeeplus.weixin.api.core.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 *
 * @description:Freemarker引擎协助类
 * Created by yuzp17311 on 2016/8/22.
 */
public class FreemarkerUtil {
	private static Configuration _tplConfig = new Configuration();

	public FreemarkerUtil(){
		
	}
	public FreemarkerUtil(String dir) {
		try {
			_tplConfig.setDirectoryForTemplateLoading(new File(dir));
			//必须freemarker字段为空，报错
			_tplConfig.setClassicCompatible(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 解析ftl
	 * 
	 * @param tplName
	 *            模板名
	 * @param encoding
	 *            编码
	 * @param paras
	 *            参数Map
	 * @return
	 */
	public String parseTemplate(String tplName, String encoding,
			Map<String, Object> paras) {
		try {
			StringWriter swriter = new StringWriter();
			Template mytpl = null;
			mytpl = _tplConfig.getTemplate(tplName, encoding);
			mytpl.process(paras, swriter);
			return swriter.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return e.toString();
		}

	}

    /**
     * 生成静态页
     * @param tplPath 模版路径
     * @param tplName 模版名
     * @param paras 参数Map
     */
	public void genStaticPage(String tplPath, String tplName,
			Map<String, Object> paras) {
		Writer out = null;
		try {
			out = new OutputStreamWriter(new FileOutputStream(tplName),"UTF-8");
			Template mytpl = null;
			mytpl = _tplConfig.getTemplate(tplPath, "UTF-8");
			mytpl.process(paras, out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

    /**
     * 解析模版
     * @param tplName 模版名
     * @param paras  参数Map
     * @return
     */
	public String parseTemplate(String tplName, Map<String, Object> paras) {
		return this.parseTemplate(tplName, "utf-8", paras);
	}
	
	/**
	 * 解析ftl
	 * @param tplContent 模板内容
	 * @param encoding 编码
	 * @param paras 参数
	 * @return String 模板解析后内容
	 */
	public String parseTemplateContent(String tplContent,
			Map<String, Object> paras, String encoding) {
		Configuration cfg = new Configuration();    
	    StringWriter writer = new StringWriter(); 
        cfg.setTemplateLoader(new StringTemplateLoader(tplContent));  
        encoding = encoding==null?"UTF-8":encoding;
        cfg.setDefaultEncoding(encoding);    
   
        Template template;
		try {
			template = cfg.getTemplate("");
	        template.process(paras, writer);    
			} catch (Exception e) {
				e.printStackTrace();
			}
        return writer.toString();       
	}
	
	/**
	 * 解析ftl
	 * @param tplContent 模板内容
	 * @param paras 参数
	 * @return String 模板解析后内容
	 */
	public static String parseTemplateContent(String tplContent,Map<String, Object> paras) {
		Configuration cfg = new Configuration();    
	    StringWriter writer = new StringWriter(); 
        cfg.setTemplateLoader(new StringTemplateLoader(tplContent));  
        cfg.setDefaultEncoding("UTF-8");    
   
        Template template;
		try {
			template = cfg.getTemplate("");
	        template.process(paras, writer);    
			} catch (Exception e) {
				e.printStackTrace();
			}
        return writer.toString();       
	}
}