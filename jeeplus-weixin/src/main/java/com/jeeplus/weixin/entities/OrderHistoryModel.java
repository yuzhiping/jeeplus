package com.jeeplus.weixin.entities;

import java.util.Date;

public class OrderHistoryModel {
    private Integer recordid;

    private String orderno;

    private Byte orderstatus;

    private Integer ordercar;

    private Integer orderuser;

    private Byte depositstatus;

    private Integer orderpay;

    private Integer calcmileage;

    private Integer validmileage;

    private String contractno;

    private String platenumber;

    private Date ordertime;

    private Date picktime;

    private String planusetime;

    private String actualusetime;

    private Integer tboxid;

    private String pickremark;

    private String rtnremark;

    private String orderoperator;

    private Date updatetime;

    public Integer getRecordid() {
        return recordid;
    }

    public void setRecordid(Integer recordid) {
        this.recordid = recordid;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public Byte getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(Byte orderstatus) {
        this.orderstatus = orderstatus;
    }

    public Integer getOrdercar() {
        return ordercar;
    }

    public void setOrdercar(Integer ordercar) {
        this.ordercar = ordercar;
    }

    public Integer getOrderuser() {
        return orderuser;
    }

    public void setOrderuser(Integer orderuser) {
        this.orderuser = orderuser;
    }

    public Byte getDepositstatus() {
        return depositstatus;
    }

    public void setDepositstatus(Byte depositstatus) {
        this.depositstatus = depositstatus;
    }

    public Integer getOrderpay() {
        return orderpay;
    }

    public void setOrderpay(Integer orderpay) {
        this.orderpay = orderpay;
    }

    public Integer getCalcmileage() {
        return calcmileage;
    }

    public void setCalcmileage(Integer calcmileage) {
        this.calcmileage = calcmileage;
    }

    public Integer getValidmileage() {
        return validmileage;
    }

    public void setValidmileage(Integer validmileage) {
        this.validmileage = validmileage;
    }

    public String getContractno() {
        return contractno;
    }

    public void setContractno(String contractno) {
        this.contractno = contractno;
    }

    public String getPlatenumber() {
        return platenumber;
    }

    public void setPlatenumber(String platenumber) {
        this.platenumber = platenumber;
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public Date getPicktime() {
        return picktime;
    }

    public void setPicktime(Date picktime) {
        this.picktime = picktime;
    }

    public String getPlanusetime() {
        return planusetime;
    }

    public void setPlanusetime(String planusetime) {
        this.planusetime = planusetime;
    }

    public String getActualusetime() {
        return actualusetime;
    }

    public void setActualusetime(String actualusetime) {
        this.actualusetime = actualusetime;
    }

    public Integer getTboxid() {
        return tboxid;
    }

    public void setTboxid(Integer tboxid) {
        this.tboxid = tboxid;
    }

    public String getPickremark() {
        return pickremark;
    }

    public void setPickremark(String pickremark) {
        this.pickremark = pickremark;
    }

    public String getRtnremark() {
        return rtnremark;
    }

    public void setRtnremark(String rtnremark) {
        this.rtnremark = rtnremark;
    }

    public String getOrderoperator() {
        return orderoperator;
    }

    public void setOrderoperator(String orderoperator) {
        this.orderoperator = orderoperator;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}