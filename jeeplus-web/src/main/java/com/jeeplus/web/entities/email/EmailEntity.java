package com.jeeplus.web.entities.email;

import java.io.File;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-08 19:03.
 */
public class EmailEntity implements Serializable {

    private Set<String> toSet;  //收件人
    private Set<String> bcc; //抄送
    private String subject;  //主题
    private String content;  //正文
    private boolean html;  //正文是否是HTML
    private Map<String, File> attachments;  //附件路径
    private boolean attachment;  //是否有附件

    public Set<String> getToSet() {
        return toSet;
    }

    public void setToSet(Set<String> toSet) {
        this.toSet = toSet;
    }

    public Set<String> getBcc() {
        return bcc;
    }

    public void setBcc(Set<String> bcc) {
        this.bcc = bcc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isHtml() {
        return html;
    }

    public void setHtml(boolean html) {
        this.html = html;
    }

    public Map<String, File> getAttachments() {
        return attachments;
    }

    public void setAttachments(Map<String, File> attachments) {
        this.attachments = attachments;
    }

    public boolean isAttachment() {
        return attachment;
    }

    public void setAttachment(boolean attachment) {
        this.attachment = attachment;
    }
}
