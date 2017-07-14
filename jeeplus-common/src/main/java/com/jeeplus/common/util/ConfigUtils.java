package com.jeeplus.common.util;

import java.util.Properties;

/**
 * 操作配置文件
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-07-14 17:47.
 */
public class ConfigUtils {

    private static Properties config;

    public static Properties getConfig() {
        return config;
    }

    public static void setConfig(Properties config) {
        ConfigUtils.config = config;
    }
}
