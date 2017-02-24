package com.jeeplus.blog.service.system.impl;

import com.jeeplus.blog.service.system.SysEmailNotifyService;
import org.springframework.stereotype.Service;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-24 14:43.
 */
@Service
public class SysEmailNotifyServiceImpl implements SysEmailNotifyService {
    /**
     * send email to many user
     *
     * @param userNamesText
     * @param mailTitle
     * @param mailContent
     * @throws Exception
     */
    @Override
    public void sendEmailToUsersByText(String userNamesText, String mailTitle, String mailContent) {

    }

    /**
     * Email to the specified target
     *
     * @param emailAddress
     * @param emailTitle
     * @param emailContent
     * @throws Exception
     */
    @Override
    public void sendEmail(String emailAddress, String emailTitle, String emailContent) {

    }
}
