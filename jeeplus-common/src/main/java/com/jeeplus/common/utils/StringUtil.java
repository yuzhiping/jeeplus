package com.jeeplus.common.utils;

import java.util.UUID;

/**
 * Created by yuzp17311 on 2016/9/12.
 */
public class StringUtil {

    public static boolean isEmpty(final String value) {
        return (null == value || value.trim().equals(""));
    }

    public static String getOnlyKey(){
        return String.valueOf(UUID.randomUUID()).replace("-", "");
    }
}
