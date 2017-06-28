package com.jeeplus.weixin.service.weixin.impl;

import com.jeeplus.weixin.entities.UserInfoModel;
import com.jeeplus.weixin.fastweixin.api.OauthAPI;
import com.jeeplus.weixin.fastweixin.api.config.ApiConfig;
import com.jeeplus.weixin.fastweixin.api.response.GetUserInfoResponse;
import com.jeeplus.weixin.mapper.UserInfoModelMapper;
import com.jeeplus.weixin.service.weixin.FwhuserService;
import com.jeeplus.weixin.utils.PropertiesUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Properties;

/**
 * Created by 余智平 on 2017/4/10.
 */
@Service
public class FwhuserServiceImpl implements FwhuserService {

    @Autowired
    private ApiConfig config;

    @Autowired
    private UserInfoModelMapper userInfoModelMapper;

    @Override
    public UserInfoModel checkOpenId(UserInfoModel userInfoModel, String openid) {
        GetUserInfoResponse userInfo =null;

        Properties prop = PropertiesUtil.getConfigProperty();

        if (StringUtils.isEmpty(openid)) {
            // 传入的openid为空的情况，直接返回false
            return null;
        } else {
            // 先判断openid是否合法
            try {
                userInfo = getUserInfo(openid);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if ("".equals(userInfo.getErrcode()) || userInfo.getErrcode() == null) {

                // 合法的openid,放入到session中
                UserInfoModel userInfoModel2 = userInfoModelMapper.selectByOpenid(openid);
                if (userInfoModel2!=null) {

                    return userInfoModel2;

                } else if(userInfoModel!=null && userInfoModel.getOpenid()==null) {
                    // 已经绑定过微信的账号不能重新绑定
                    userInfoModel.setOpenid(openid);
                    userInfoModelMapper.updateByPrimaryKey(userInfoModel);
                    return userInfoModel;
                }

            }

            return null;


        }
    }

    private GetUserInfoResponse getUserInfo(String openid) throws Exception {
        GetUserInfoResponse userInfo;
        OauthAPI oauthAPI=new OauthAPI(config);
        userInfo = oauthAPI.getUserInfo(config.getAccessToken(),openid);
        return userInfo;
    }

}
