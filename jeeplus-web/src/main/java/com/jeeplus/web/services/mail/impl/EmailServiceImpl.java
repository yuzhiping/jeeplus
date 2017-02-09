package com.jeeplus.web.services.mail.impl;

import com.jeeplus.web.entities.email.EmailEntity;
import com.jeeplus.web.services.mail.EmailService;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Map;
import java.util.Set;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-08 19:01.
 */
@Service
public class EmailServiceImpl implements EmailService {


    @Value("${mailserver.google.username}")
    private String from; //发送者

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private VelocityEngine velocityEngine;

    @Autowired
    private SpringTemplateEngine thymeleafEngine;
    /**
     * 发送邮件
     *
     * @param emailEntity
     */
    @Override
    public void sendEmail(EmailEntity emailEntity) throws MessagingException {

        MimeMessage message=mailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(message,emailEntity.isAttachment());

        /**
         * 添加发送者
         */
        helper.setFrom(from);

        Set<String> toSet =emailEntity.getToSet();
        /**
         * 添加接收者
         */
        helper.setTo(toSet.toArray(new String[toSet.size()]));

        /**
         * 添加主题
         */
        helper.setSubject(emailEntity.getSubject());
        /**
         * 添加正文
         */
        helper.setText(emailEntity.getContent(),emailEntity.isHtml());

        /**
         * 添加附件
         */
        if(emailEntity.isAttachment()){
            Map<String, File> attachments = emailEntity.getAttachments();

            if(attachments != null){
                for(Map.Entry<String, File> attach : attachments.entrySet()){
                    helper.addAttachment(attach.getKey(), attach.getValue());
                }

            }

        }

        mailSender.send(message);  //发送

    }

    /**
     * 使用Velocity模板发送邮件
     *
     * @param emailEntity      简单邮件详情
     * @param model            模板参数
     * @param templateLocation 模板所在路径
     * @throws MessagingException
     */
    @Override
    public void sendVelocityEmail(EmailEntity emailEntity, Map<String, Object> model, 
                                  String templateLocation) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, emailEntity.isAttachment());

        /**
         * 添加发送者
         */
        helper.setFrom(from);

        Set<String> toSet =emailEntity.getToSet();
        /**
         * 添加接收者
         */
        helper.setTo(toSet.toArray(new String[toSet.size()]));

        /**
         * 添加主题
         */
        helper.setSubject(emailEntity.getSubject());
        /**
         * 添加正文
         */
        String emailContent = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateLocation, "UTF-8", model);
        helper.setText(emailContent,true);

        /**
         * 添加附件
         */
        if(emailEntity.isAttachment()){
            Map<String, File> attachments = emailEntity.getAttachments();

            if(attachments != null){
                for(Map.Entry<String, File> attach : attachments.entrySet()){
                    helper.addAttachment(attach.getKey(), attach.getValue());
                }
            }
        }
        mailSender.send(message);  //发送
        
    }

    public void sendThymeleafEmail(EmailEntity simpleEmail, Map<String, Object> model, String templateLocation)
            throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, simpleEmail.isAttachment());

        /**
         * 添加发送者
         */
        helper.setFrom(from);

        Set<String> toSet =simpleEmail.getToSet();
        /**
         * 添加接收者
         */
        helper.setTo(toSet.toArray(new String[toSet.size()]));

        /**
         * 添加主题
         */
        helper.setSubject(simpleEmail.getSubject());
        /**
         * 添加正文
         */
        if(model != null){
            Context context = new Context();
            for(Map.Entry<String, Object> param : model.entrySet()){
                context.setVariable(param.getKey(), param.getValue());
            }

            String emailContent = thymeleafEngine.process("emailTemplate", context);
            helper.setText(emailContent,true);
        }

        /**
         * 添加附件
         */
        if(simpleEmail.isAttachment()){
            Map<String, File> attachments = simpleEmail.getAttachments();

            if(attachments != null){
                for(Map.Entry<String, File> attach : attachments.entrySet()){
                    helper.addAttachment(attach.getKey(), attach.getValue());
                }
            }
        }
        mailSender.send(message);  //发送

    }
}
