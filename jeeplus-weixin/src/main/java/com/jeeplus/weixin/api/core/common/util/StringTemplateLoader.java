package com.jeeplus.weixin.api.core.common.util;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import freemarker.cache.TemplateLoader;

/**
 * 模版加载接口的实现
 * Created by yuzp17311 on 2016/8/22.
 */
public class StringTemplateLoader implements TemplateLoader {

		private static final String DEFAULT_TEMPLATE_KEY = "_default_template_key";
		private Map templates = new HashMap();

		public StringTemplateLoader(String defaultTemplate) {
			if (defaultTemplate != null && !defaultTemplate.equals("")) {
				templates.put(DEFAULT_TEMPLATE_KEY, defaultTemplate);
			}
		}

	/**
	 * 新增模版
	 * @param name 名称
	 * @param template 模版
	 */
		public void AddTemplate(String name, String template) {
			if (name == null || template == null || name.equals("")
					|| template.equals("")) {
				return;
			}
			if (!templates.containsKey(name)) {
				templates.put(name, template);
			}
		}

	/**
	 * 关闭模版
	 * @param templateSource 模版源
	 * @throws IOException
	 */
		public void closeTemplateSource(Object templateSource)
				throws IOException {

		}

	/**
	 * 查找模版
	 * @param name  名称
	 * @return
	 * @throws IOException
	 */
		public Object findTemplateSource(String name) throws IOException {
			if (name == null || name.equals("")) {
				name = DEFAULT_TEMPLATE_KEY;
			}
			return templates.get(name);
		}

		public long getLastModified(Object templateSource) {
			return 0;
		}

		public Reader getReader(Object templateSource, String encoding)
				throws IOException {
			return new StringReader((String) templateSource);
		}

	}

