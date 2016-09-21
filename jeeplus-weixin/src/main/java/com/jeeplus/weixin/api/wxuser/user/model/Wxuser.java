package com.jeeplus.weixin.api.wxuser.user.model;


/**   
 * @Title: Entity
 * @Description: 微信服务号关注用户union信息
 * @author onlineGenerator
 * @date 2015-01-25 19:28:24
 * @version V1.0   
 *
 */
public class Wxuser {
	
	/**用户是否订阅*/
	private Integer subscribe;
	/**用户的标识*/
	private String openid;
	/**用户的昵称*/
	private String nickname;
	/**性别*/
	private String sex;
	/**用户所在城市*/
	private String city;
	/**用户所在国家*/
	private String country;
	/**用户所在省份*/
	private String province;
	/**用户的语言zh_CN*/
	private String language;
	/**用户头像*/
	private String headimgurl;
	/**用户关注时间*/
	private String subscribe_time;
	/**公众号*/
	private String unionid;
	
	private String remark;
	
	public Integer getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(Integer subscribe) {
		this.subscribe = subscribe;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	
	public String getSubscribe_time() {
		return subscribe_time;
	}
	public void setSubscribe_time(String subscribe_time) {
		this.subscribe_time = subscribe_time;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
