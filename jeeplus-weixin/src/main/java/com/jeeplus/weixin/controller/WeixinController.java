package com.jeeplus.weixin.controller;

import com.jeeplus.weixin.api.core.common.AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by yuzp17311 on 2016/8/23.
 */
@Controller
@RequestMapping("/weixin")
public class WeixinController {

    private static Logger logger= LoggerFactory.getLogger(WeixinController.class);

    private static String appid;
    private static String appscret;

    static {
        Properties properties=new Properties();
        InputStream inputStream=Object.class.getResourceAsStream("classpath:weixin/weixin.properties");
        try {
            properties.load(inputStream);
            appid=properties.getProperty("appid");
            appscret=properties.getProperty("appscret");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/wxGet",method = RequestMethod.GET)
    public void weixinGet(){

        AccessToken accessToken=new AccessToken(appid,appscret);

    }

}
