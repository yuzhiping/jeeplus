package com.jeeplus.blog.entities;

import java.io.Serializable;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-23 17:02.
 */
public class BaseAgent implements Serializable {

    private Integer id;
    private String agentKey;
    private String agentBrowserName;
    private String agentBrowserVersion;
    private String agentBrowserEngine;
    private String agentOs;
    private String agentDevice;


    @Override
    public String toString() {
        return "BaseAgent{" +
                "id=" + id +
                ", agentKey='" + agentKey + '\'' +
                ", agentBrowserName='" + agentBrowserName + '\'' +
                ", agentBrowserVersion='" + agentBrowserVersion + '\'' +
                ", agentBrowserEngine='" + agentBrowserEngine + '\'' +
                ", agentOs='" + agentOs + '\'' +
                ", agentDevice='" + agentDevice + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAgentKey() {
        return agentKey;
    }

    public void setAgentKey(String agentKey) {
        this.agentKey = agentKey;
    }

    public String getAgentBrowserName() {
        return agentBrowserName;
    }

    public void setAgentBrowserName(String agentBrowserName) {
        this.agentBrowserName = agentBrowserName;
    }

    public String getAgentBrowserVersion() {
        return agentBrowserVersion;
    }

    public void setAgentBrowserVersion(String agentBrowserVersion) {
        this.agentBrowserVersion = agentBrowserVersion;
    }

    public String getAgentBrowserEngine() {
        return agentBrowserEngine;
    }

    public void setAgentBrowserEngine(String agentBrowserEngine) {
        this.agentBrowserEngine = agentBrowserEngine;
    }

    public String getAgentOs() {
        return agentOs;
    }

    public void setAgentOs(String agentOs) {
        this.agentOs = agentOs;
    }

    public String getAgentDevice() {
        return agentDevice;
    }

    public void setAgentDevice(String agentDevice) {
        this.agentDevice = agentDevice;
    }
}
