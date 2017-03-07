package com.jeeplus.weixin.dto;


import com.jeeplus.weixin.entities.OrderInfoModel;

/**
 * 我的订单详情模型
 * @author lzh
 *
 */
public class OrderDetailModel extends OrderInfoModel {

	private String carpicname;
	
	private String orderstatus_name;
	
	private String depositstatus_name;
	
	private String supplycarname;
	
	private String cityName;
	
	private String providerphoneno;

	private Integer	deposit;
	
	private Integer paytype;
 
	private String refferno; //推荐人手机号
 
	private Integer fee;    //车辆单价
	
	public String getCarpicname() {
		return carpicname;
	}

	public void setCarpicname(String carpicname) {
		this.carpicname = carpicname;
	}

	public String getOrderstatus_name() {
		return orderstatus_name;
	}

	public void setOrderstatus_name(String orderstatus_name) {
		this.orderstatus_name = orderstatus_name;
	}

	public String getDepositstatus_name() {
		return depositstatus_name;
	}

	public void setDepositstatus_name(String depositstatus_name) {
		this.depositstatus_name = depositstatus_name;
	}

	public String getSupplycarname() {
		return supplycarname;
	}

	public void setSupplycarname(String supplycarname) {
		this.supplycarname = supplycarname;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getProviderphoneno() {
		return providerphoneno;
	}

	public void setProviderphoneno(String providerphoneno) {
		this.providerphoneno = providerphoneno;
	}

	
	
	public Integer getDeposit() {
		return deposit;
	}

	public void setDeposit(Integer deposit) {
		this.deposit = deposit;
	}

	public Integer getPaytype() {
		return paytype;
	}

	public void setPaytype(Integer paytype) {
		this.paytype = paytype;
	}

	public String getRefferno() {
		return refferno;
	}

	public void setRefferno(String refferno) {
		this.refferno = refferno;
	}

	public Integer getFee() {
		return fee;
	}

	public void setFee(Integer fee) {
		this.fee = fee;
	}
 
 
	
}
