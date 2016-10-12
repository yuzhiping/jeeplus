package com.jeeplus.weixin.fastweixin.handle.impl;

import com.jeeplus.weixin.fastweixin.handle.MessageHandle;
import com.jeeplus.weixin.fastweixin.message.BaseMsg;
import com.jeeplus.weixin.fastweixin.message.req.BaseReqMsg;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2016-10-12 18:38.
 */
public class WeiXinMessageHandle implements MessageHandle {
    /**
     * 处理微信消息
     *
     * @param message 微信消息
     * @return 回复用户的消息
     */
    @Override
    public BaseMsg handle(BaseReqMsg message) {
        return null;
    }

    /**
     * 在处理之前，判断本条消息是否符合处理的条件
     *
     * @param message 消息
     * @return 是否需要处理
     */
    @Override
    public boolean beforeHandle(BaseReqMsg message) {
        return false;
    }
}
