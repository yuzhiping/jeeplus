package com.jeeplus.weixin.dto;


import com.jeeplus.weixin.entities.SupplyCarModel;

public class IndexModel extends SupplyCarModel {

	private String  cityName;
	
    private String carmodel;
    
    private String  feeunitname;
    
    private Integer fee;
    
    private String   carpicname;
    
    private String  macrangepercharge; //续航里程
    
    private String  loadweight;
 
    private Integer  providerid;  //生产厂商编号
    
    private String   providerphoneno; //生产厂商在某个城市下的联系电话
    
    private String  vehicleappname;
    
    private Integer  carrypassengers; //载客数
    
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCarmodel() {
		return carmodel;
	}

	public void setCarmodel(String carmodel) {
		this.carmodel = carmodel;
	}

	public String getFeeunitname() {
		return feeunitname;
	}

	public void setFeeunitname(String feeunitname) {
		this.feeunitname = feeunitname;
	}

	public Integer getFee() {
		return fee;
	}

	public void setFee(Integer fee) {
		this.fee = fee;
	}

	public String getCarpicname() {
		return carpicname;
	}

	public void setCarpicname(String carpicname) {
		this.carpicname = carpicname;
	}

	public String getMacrangepercharge() {
		return macrangepercharge;
	}

	public void setMacrangepercharge(String macrangepercharge) {
		this.macrangepercharge = macrangepercharge;
	}

	public String getLoadweight() {
		return loadweight;
	}

	public void setLoadweight(String loadweight) {
		this.loadweight = loadweight;
	}

	public Integer getProviderid() {
		return providerid;
	}

	public void setProviderid(Integer providerid) {
		this.providerid = providerid;
	}

	public String getProviderphoneno() {
		return providerphoneno;
	}

	public void setProviderphoneno(String providerphoneno) {
		this.providerphoneno = providerphoneno;
	}

	public String getVehicleappname() {
		return vehicleappname;
	}

	public void setVehicleappname(String vehicleappname) {
		this.vehicleappname = vehicleappname;
	}

	public Integer getCarrypassengers() {
		return carrypassengers;
	}

	public void setCarrypassengers(Integer carrypassengers) {
		this.carrypassengers = carrypassengers;
	}
    
    
    
	
}
