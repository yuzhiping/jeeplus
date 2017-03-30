package com.jeeplus.web.commons.validator;

import com.jeeplus.web.commons.exception.RRException;
import org.apache.commons.lang3.StringUtils;

/**
 * 数据校验
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-03-30 11:38.
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new RRException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new RRException(message);
        }
    }

}
