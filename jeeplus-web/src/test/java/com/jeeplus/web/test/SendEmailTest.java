package com.jeeplus.web.test;

import com.jeeplus.web.entities.email.EmailEntity;
import com.jeeplus.web.service.mail.EmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.MessagingException;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-09 16:42.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-email-test.xml"})
public class SendEmailTest {

    @Autowired
    private EmailService emailService;


    /**
     * 发送简单邮件
     * @throws MessagingException
     */
    @Test
    public void sendSimpleEmail() throws MessagingException {

        EmailEntity simpleEmail = new EmailEntity();
        simpleEmail.setSubject("测试在Spring中发送邮件");

        Set<String> receivers = new HashSet<>();
        receivers.add("18883182757@163.com");
        simpleEmail.setToSet(receivers);

        simpleEmail.setHtml(false);
        simpleEmail.setContent("Netty是由JBOSS提供的一个java开源框架。Netty提供异步的、"
                + "事件驱动的网络应用程序框架和工具，用以快速开发高性能、高可靠性的网络服务器和客户端程序。");

        simpleEmail.setAttachment(false);

        emailService.sendEmail(simpleEmail);
    }

    /**
     * 发送带附件的邮件
     * @throws MessagingException
     */
    @Test
    public void sendEmailWithAttachment() throws MessagingException {
        EmailEntity simpleEmail = new EmailEntity();
        simpleEmail.setSubject("测试在Spring中发送带有附件的邮件");

        Set<String> receivers = new HashSet<>();
        receivers.add("18883182757@163.com");
        simpleEmail.setToSet(receivers);

        simpleEmail.setHtml(false);
        simpleEmail.setContent(
                "Kafka是一种高吞吐量的分布式发布订阅消息系统，它可以处理消费者规模的网站中的所有动作流数据。 "
                        + "这种动作（网页浏览，搜索和其他用户的行动）是在现代网络上的许多社会功能的一个关键因素。 "
                        + "这些数据通常是由于吞吐量的要求而通过处理日志和日志聚合来解决。 "
                        + "对于像Hadoop的一样的日志数据和离线分析系统，但又要求实时处理的限制，这是一个可行的解决方案。"
                        + "Kafka的目的是通过Hadoop的并行加载机制来统一线上和离线的消息处理，也是为了通过集群来提供实时的消费。");

        simpleEmail.setAttachment(true);

        Map<String, File> attachments = new HashMap<>();  //附件集合
        /**
         * web项目中使用request.getSession().getServletContext().getRealPath("/uploads")获取路径
         */
        File sockjs = new File("C:\\Users\\yuzp17311\\Desktop\\优化后的SQL.sql");
        attachments.put(sockjs.getName(), sockjs);

        File stomp = new File("C:\\Users\\yuzp17311\\Desktop\\input.xml");
        attachments.put(stomp.getName(), stomp);

        File jquery = new File("C:\\Users\\yuzp17311\\Desktop\\历史数据导入.docx");
        attachments.put(jquery.getName(), jquery);

        simpleEmail.setAttachments(attachments);

        emailService.sendEmail(simpleEmail);
    }

    /**
     * 发送Velocity模板的邮件
     * @throws MessagingException
     */
    @Test
    public void sendVelocityEmail() throws MessagingException {
        EmailEntity simpleEmail = new EmailEntity();
        simpleEmail.setSubject("测试在Spring中发送使用了Velocity模板的邮件");

        Set<String> receivers = new HashSet<>();
        receivers.add("18883182757@163.com");
        simpleEmail.setToSet(receivers);

        Map<String, Object> params = new HashMap<>();
        params.put("title", "This's title");
        params.put("hello", "测试在Spring中发送使用了Velocity模板的邮件");

        simpleEmail.setAttachment(false);

        emailService.sendVelocityEmail(simpleEmail, params, "emailTemplate.vm");
    }


    /**
     * 发送Thymeleaf模板的邮件
     * @throws MessagingException
     */
    @Test
    public void sendThymeleafEmail() throws MessagingException {
        EmailEntity simpleEmail = new EmailEntity();
        simpleEmail.setSubject("测试在Spring中发送使用了Thymeleaf模板的邮件");

        Set<String> receivers = new HashSet<>();
        receivers.add("18883182757@163.com");
        simpleEmail.setToSet(receivers);

        Map<String, Object> params = new HashMap<>();
        params.put("title", "This's title");
        params.put("hello", "测试在Spring中发送使用了Thymeleaf模板的邮件");

        simpleEmail.setAttachment(false);

        emailService.sendThymeleafEmail(simpleEmail, params, "emailTemplate.html");
    }

}
