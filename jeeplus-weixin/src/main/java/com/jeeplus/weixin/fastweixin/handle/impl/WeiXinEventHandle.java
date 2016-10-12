package com.jeeplus.weixin.fastweixin.handle.impl;

import com.jeeplus.weixin.fastweixin.handle.EventHandle;
import com.jeeplus.weixin.fastweixin.message.BaseMsg;
import com.jeeplus.weixin.fastweixin.message.req.BaseEvent;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2016-10-12 18:41.
 */
public class WeiXinEventHandle implements EventHandle {
    /**
     * 处理微信事件
     *
     * @param event 微信事件
     * @return 回复用户的消息
     */
    @Override
    public BaseMsg handle(BaseEvent event) {
        return null;
    }

    /**
     * 在处理之前，判断本事件是否符合处理的条件
     *
     * @param event 事件
     * @return 是否需要处理
     */
    @Override
    public boolean beforeHandle(BaseEvent event) {
        return false;
    }
}
