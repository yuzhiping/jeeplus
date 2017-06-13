package com.jeeplus.weixin.service.weixin;

import com.jeeplus.weixin.entities.UserInfoModel;

/**
 * Created by 余智平 on 2017/4/10.
 */
public interface FwhuserService {

    UserInfoModel checkOpenId(UserInfoModel userInfoModel, String openid);

}
