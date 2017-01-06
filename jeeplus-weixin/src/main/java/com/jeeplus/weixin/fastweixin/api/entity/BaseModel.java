package com.jeeplus.weixin.fastweixin.api.entity;

import com.jeeplus.weixin.fastweixin.util.JSONUtils;

/**
 * 抽象实体类
 *
 * @author peiyu
 */
public abstract class BaseModel implements Model {
    @Override
    public String toJsonString() {
        return JSONUtils.toJson(this);
    }
}
