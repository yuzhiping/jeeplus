package com.jeeplus.weixin.fastweixin.api.response;

import com.jeeplus.weixin.fastweixin.api.entity.Tag;

/**
 * @author peiyu
 * @since 1.3.9
 */
public class CreateTagResponse extends BaseResponse {

    private Tag tag;

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
