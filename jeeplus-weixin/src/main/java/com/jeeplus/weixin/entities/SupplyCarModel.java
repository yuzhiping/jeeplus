package com.jeeplus.weixin.entities;

import java.util.Date;
import org.apache.commons.lang3.StringUtils;

public class SupplyCarModel extends BaseModel {

    private Integer recordid;

    private Integer carprovider;
    
    private String carsupplycity;

    private Integer supplycarapptype;

    private Integer supplycarmodel;

    private String supplycarname;

    private String phoneno;

    private Integer feescheme;

    private Integer displayseq;

    private Byte deletestatus;

    private Date updatetime;

    private String providerphoneno;
   
    public Integer getRecordid() {
        return recordid;
    }

    public void setRecordid(Integer recordid) {
        this.recordid = recordid;
    }

    public Integer getCarprovider() {
        return carprovider;
    }

    public void setCarprovider(Integer carprovider) {
        this.carprovider = carprovider;
    }

    public String getCarsupplycity() {
        return carsupplycity;
    }

    public void setCarsupplycity(String carsupplycity) {
        this.carsupplycity = carsupplycity;
    }

    public Integer getSupplycarapptype() {
        return supplycarapptype;
    }

    public void setSupplycarapptype(Integer supplycarapptype) {
        this.supplycarapptype = supplycarapptype;
    }

    public Integer getSupplycarmodel() {
        return supplycarmodel;
    }

    public void setSupplycarmodel(Integer supplycarmodel) {
        this.supplycarmodel = supplycarmodel;
    }

    public String getSupplycarname() {
        return supplycarname;
    }

    public void setSupplycarname(String supplycarname) {
    	
    	//只用第一张图片
    	if(!StringUtils.isEmpty(supplycarname)){
    		
    		supplycarname=supplycarname.split(",")[0];
    	}
    	
        this.supplycarname = supplycarname;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public Integer getFeescheme() {
        return feescheme;
    }

    public void setFeescheme(Integer feescheme) {
        this.feescheme = feescheme;
    }

    public Integer getDisplayseq() {
        return displayseq;
    }

    public void setDisplayseq(Integer displayseq) {
        this.displayseq = displayseq;
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

	public String getProviderphoneno() {
		return providerphoneno;
	}

	public void setProviderphoneno(String providerphoneno) {
		this.providerphoneno = providerphoneno;
	}
 
 
    
}