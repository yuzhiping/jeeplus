package com.jeeplus.weixin.entities;

import java.util.Date;

/**
 * Created by 余智平 on 2017/3/7.
 */
public class CarInfoModel {

    private Integer recordid;

    private String carmodel;

    private String carpicname;

    private Short macrangepercharge;

    private Short carrypassengers;

    private Short loadweight;

    private Integer carprovider;

    private String carname;

    private Byte deletestatus;

    private Date updatetime;

    public Integer getRecordid() {
        return recordid;
    }

    public void setRecordid(Integer recordid) {
        this.recordid = recordid;
    }

    public String getCarmodel() {
        return carmodel;
    }

    public void setCarmodel(String carmodel) {
        this.carmodel = carmodel;
    }

    public String getCarpicname() {
        return carpicname;
    }

    public void setCarpicname(String carpicname) {
        this.carpicname = carpicname;
    }

    public Short getMacrangepercharge() {
        return macrangepercharge;
    }

    public void setMacrangepercharge(Short macrangepercharge) {
        this.macrangepercharge = macrangepercharge;
    }

    public Short getCarrypassengers() {
        return carrypassengers;
    }

    public void setCarrypassengers(Short carrypassengers) {
        this.carrypassengers = carrypassengers;
    }

    public Short getLoadweight() {
        return loadweight;
    }

    public void setLoadweight(Short loadweight) {
        this.loadweight = loadweight;
    }

    public Integer getCarprovider() {
        return carprovider;
    }

    public void setCarprovider(Integer carprovider) {
        this.carprovider = carprovider;
    }

    public String getCarname() {
        return carname;
    }

    public void setCarname(String carname) {
        this.carname = carname;
    }

    public Byte getDeletestatus() {
        return deletestatus;
    }

    public void setDeletestatus(Byte deletestatus) {
        this.deletestatus = deletestatus;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

}
