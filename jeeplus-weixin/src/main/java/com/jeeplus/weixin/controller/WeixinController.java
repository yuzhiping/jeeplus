package com.jeeplus.weixin.controller;

import com.jeeplus.weixin.fastweixin.api.UserAPI;
import com.jeeplus.weixin.fastweixin.api.config.ApiConfig;
import com.jeeplus.weixin.fastweixin.api.response.GetUserInfoResponse;
import com.jeeplus.weixin.fastweixin.handle.EventHandle;
import com.jeeplus.weixin.fastweixin.handle.MessageHandle;
import com.jeeplus.weixin.fastweixin.handle.impl.WeiXinEventHandle;
import com.jeeplus.weixin.fastweixin.handle.impl.WeiXinMessageHandle;
import com.jeeplus.weixin.fastweixin.message.BaseMsg;
import com.jeeplus.weixin.fastweixin.message.TextMsg;
import com.jeeplus.weixin.fastweixin.message.req.*;
import com.jeeplus.weixin.fastweixin.servlet.WeixinControllerSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2016-10-12 16:59.
 */
@Controller
@RequestMapping("/weixin")
public class WeixinController extends WeixinControllerSupport {



    private static final Logger log = LoggerFactory.getLogger(WeixinController.class);

    @Value("${weixin.token}")
    private String TOKEN; //设置TOKEN，用于绑定微信服务器

    @Autowired
    private ApiConfig config;

    /**
     * 子类提供token用于绑定微信公众平台
     *
     * @return token值
     */
    @Override
    protected String getToken() {
        return TOKEN;
    }


    //使用安全模式时设置：APPID
    //不再强制重写，有加密需要时自行重写该方法
    @Override
    protected String getAppId() {
        return null;
    }
    //使用安全模式时设置：密钥
    //不再强制重写，有加密需要时自行重写该方法
    @Override
    protected String getAESKey() {
        return null;
    }
    //重写父类方法，处理对应的微信消息
    @Override
    protected BaseMsg handleTextMsg(TextReqMsg msg) {
        String content = msg.getContent();
        log.debug("用户发送到服务器的内容:{}", content);
        return new TextMsg("服务器回复用户消息!");
    }
    /*1.1版本新增，重写父类方法，加入自定义微信消息处理器
     *不是必须的，上面的方法是统一处理所有的文本消息，如果业务觉复杂，上面的会显得比较乱
     *这个机制就是为了应对这种情况，每个MessageHandle就是一个业务，只处理指定的那部分消息
     */
    @Override
    protected List<MessageHandle> initMessageHandles() {
        List<MessageHandle> handles = new ArrayList<MessageHandle>();
        handles.add(new WeiXinMessageHandle());
        return handles;
    }
    //1.1版本新增，重写父类方法，加入自定义微信事件处理器，同上
    @Override
    protected List<EventHandle> initEventHandles() {
        List<EventHandle> handles = new ArrayList<EventHandle>();
        handles.add(new WeiXinEventHandle());
        return handles;
    }


    /**
     * 绑定服务器-GET方式
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/bind",method = RequestMethod.GET)
    @ResponseBody
    public String httpGet(HttpServletRequest request, HttpServletResponse response){

        if(isLegal(request)){
            //绑定微信服务器成功
            return  request.getParameter("echostr");
        }else {
            //绑定微信服务器失败
            return "";
        }
    }


    /**
     * 处理数据请求-POST方式
     * @param request http 请求对象
     * @param response http 响应对象
     * @return
     */
    @RequestMapping(value = "/bind",method = RequestMethod.POST,produces = MediaType.TEXT_XML_VALUE+";charset=utf-8")
    @ResponseBody
    public String httpPost(HttpServletRequest request,HttpServletResponse response){

        log.debug("数据请求开始------>");
        if(isLegal(request)){
            String result=processRequest(request);
            log.debug("请求的数据为:"+result);
            return result;
        }else{
            log.debug("请求的数据为空");
            return "";
        }

    }

    /**
     *
     * @param event 添加关注事件对象
     * @return
     */
    @Override
    protected BaseMsg handleSubscribe(BaseEvent event){

        log.debug("关注事件------>");
        BaseMsg baseMsg=new TextMsg("欢迎关注Jeeplus平台服务").addLink("点击这里了解更多","www.baidu.com");
        return baseMsg;
    }

    /**
     *
     * @param event 取消关注事件对象
     * @return
     */
    @Override
    protected BaseMsg handleUnsubscribe(BaseEvent event) {
        // TODO Auto-generated method stub
        log.debug("取消关注事件...");
        return super.handleUnsubscribe(event);
    }

    /**
     *
     * @param event 菜单跳转事件对象
     * @return
     */
    @Override
    protected BaseMsg handleMenuViewEvent(MenuEvent event) {
        log.debug("跳转事件...");
        String openid = event.getFromUserName();
        log.info("用户openid ： " + openid);
        GetUserInfoResponse userInfo = new UserAPI(config).getUserInfo(openid);
        log.debug("openid 获取 unionid ： " + userInfo.getUnionid());

        // ... 自己的业务逻辑

        BaseMsg baseMsg = new TextMsg("文本消息");
        baseMsg.setMsgType(ReqType.TEXT);
        return baseMsg;
    }


    /**
     *
     * @param event 地理位置事件对象
     * @return
     */
    @Override
    protected BaseMsg handleLocationEvent(LocationEvent event) {
        // TODO Auto-generated method stub
        log.debug("位置事件...");
        return super.handleLocationEvent(event);
    }

    /**
     *
     * @param event 菜单扫描推事件对象
     * @return
     */
    @Override
    protected BaseMsg handleScanCodeEvent(ScanCodeEvent event) {
        log.debug("扫描事件...");
        return super.handleScanCodeEvent(event);
    }

    /**
     *
     * @param event 菜单弹出相册事件
     * @return
     */
    @Override
    protected BaseMsg handleTemplateMsgEvent(TemplateMsgEvent event) {
        log.debug("消息模版...");
        return super.handleTemplateMsgEvent(event);
    }


}
