package com.jeeplus.blog.entities;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-23 17:20.
 */
public class BlogLable implements Serializable {


    private static final long serialVersionUID = 1L;
    private String id;
    private String lableName;
    private String lableCreator;
    private Timestamp lableDatetime;

    @Override
    public String toString() {
        return "BlogLable{" +
                "id='" + id + '\'' +
                ", lableName='" + lableName + '\'' +
                ", lableCreator='" + lableCreator + '\'' +
                ", lableDatetime=" + lableDatetime +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLableName() {
        return lableName;
    }

    public void setLableName(String lableName) {
        this.lableName = lableName;
    }

    public String getLableCreator() {
        return lableCreator;
    }

    public void setLableCreator(String lableCreator) {
        this.lableCreator = lableCreator;
    }

    public Timestamp getLableDatetime() {
        return lableDatetime;
    }

    public void setLableDatetime(Timestamp lableDatetime) {
        this.lableDatetime = lableDatetime;
    }
}
