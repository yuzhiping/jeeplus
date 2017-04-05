package com.jeeplus.web.service.mail;

import com.jeeplus.web.entities.email.EmailEntity;

import javax.mail.MessagingException;
import java.util.Map;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-08 19:00.
 */
public interface EmailService {

    /**
     * 发送邮件
     * @param emailEntity
     */
    void sendEmail(EmailEntity emailEntity) throws MessagingException;

    /**
     * 使用Velocity模板发送邮件
     * @param emailEntity 简单邮件详情
     * @param model 模板参数
     * @param templateLocation 模板所在路径
     * @throws MessagingException
     */
    void sendVelocityEmail(EmailEntity emailEntity, Map<String, Object> model, String templateLocation) throws MessagingException;

    /**
     * 使用Thymeleaf模板发送邮件
     * @param emailEntity 简单邮件详情
     * @param model 模板参数
     * @param templateLocation 模板所在路径
     * @throws MessagingException
     */
    void sendThymeleafEmail(EmailEntity emailEntity,Map<String, Object> model,String templateLocation) throws MessagingException;

}
