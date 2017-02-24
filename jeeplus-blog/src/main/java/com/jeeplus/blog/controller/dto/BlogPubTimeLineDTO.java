package com.jeeplus.blog.controller.dto;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-23 19:58.
 */
public class BlogPubTimeLineDTO {

    private String time; //时-分
    private String content;
    private String type;
    private String date; //年月日

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "BlogPubTimeLineDTO{" +
                "time='" + time + '\'' +
                ", content='" + content + '\'' +
                ", type='" + type + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
