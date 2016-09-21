package com.jeeplus.admin.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuzp17311 on 2016/9/8.
 */
public enum ResponseErrorEnum {
    ILLEGAL_PARAMS("ILLEGAL_PARAMS", "请求参数不合法!"),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "接口内部异常!");

    ResponseErrorEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private Map<String,Object> serialize(){
        Map<String, Object> valueMap = new HashMap<>(2);
        valueMap.put("code", this.code);
        valueMap.put("message", this.message);
        return valueMap;
    }

    private String code;

    private String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
