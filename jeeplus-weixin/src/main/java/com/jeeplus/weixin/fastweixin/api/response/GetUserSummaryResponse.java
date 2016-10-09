package com.jeeplus.weixin.fastweixin.api.response;

import com.jeeplus.weixin.fastweixin.api.entity.UserSummary;

import java.util.List;

/**
 * @author peiyu
 */
public class GetUserSummaryResponse extends BaseResponse {

    private List<UserSummary> list;

    public List<UserSummary> getList() {
        return list;
    }

    public void setList(List<UserSummary> list) {
        this.list = list;
    }
}
