package com.jeeplus.weixin.utils.config;

import java.io.IOException;
import java.util.Properties;

public class PropertyPlaceholderConfigurer extends
org.springframework.beans.factory.config.PropertyPlaceholderConfigurer {
	
	private static Properties props;
	
	public Properties mergeProperties() throws IOException {
		props = super.mergeProperties();
		Property.init(props);
		return props;
	}
	
	public static String getProperty(String key) {
		return props.getProperty(key);
	}
	
	public String getProperty(String key, String defaultValue) {
		return props.getProperty(key, defaultValue);
	
	}

} 