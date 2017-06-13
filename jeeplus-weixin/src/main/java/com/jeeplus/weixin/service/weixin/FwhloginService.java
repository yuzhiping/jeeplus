package com.jeeplus.weixin.service.weixin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 余智平 on 2017/4/10.
 */
public interface FwhloginService {
    void getWxLoginuser(HttpServletRequest request, HttpServletResponse response);
}
