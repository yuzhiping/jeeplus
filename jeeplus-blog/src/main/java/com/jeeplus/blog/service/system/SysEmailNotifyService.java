package com.jeeplus.blog.service.system;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-24 14:40.
 */
public interface SysEmailNotifyService {

    /**
     * send email to many user
     * @param userNamesText
     * @param mailTitle
     * @param mailContent
     * @throws Exception
     */
    void sendEmailToUsersByText(String userNamesText,String mailTitle,String mailContent);


    /**
     * Email to the specified target
     * @param emailAddress
     * @param emailTitle
     * @param emailContent
     * @throws Exception
     */
    void sendEmail(String emailAddress, String emailTitle, String emailContent);

}
