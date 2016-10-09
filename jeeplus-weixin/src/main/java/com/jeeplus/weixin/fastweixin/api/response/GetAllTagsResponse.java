package com.jeeplus.weixin.fastweixin.api.response;

import com.jeeplus.weixin.fastweixin.api.entity.Tag;

import java.util.List;

/**
 * @author peiyu
 * @since 1.3.9
 */
public class GetAllTagsResponse extends BaseResponse {

    private List<Tag> tags;

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
