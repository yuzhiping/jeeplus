package com.jeeplus.weixin.entities;

import java.util.Date;

public class RewardDetailModel extends BaseModel{
	private Integer recordid;
	
	private Integer rewarduser;
	
	private String rewardfromuserno;
	
	private Byte rewardfromuserlevel;
	
	private Integer reward;
	
	private String rewarddesc;
	
	private Date updatetime;

	public Integer getRecordid(){
		return recordid;
	}
	
	public void setRecordid(Integer recordid){
		this.recordid=recordid;
	}
	
	public Integer getRewarduser(){
		return rewarduser;
	}
	
	public void setRewarduser(Integer rewarduser){
		this.rewarduser=rewarduser;
	}
	
	public String getRewardfromuserno(){
		return rewardfromuserno;
	}
	
	public void setRewardfromuserno(String rewardfromuserno){
		this.rewardfromuserno=rewardfromuserno;
	}
	
	public Byte getRewardfromuserlevel(){
		return rewardfromuserlevel;
	}
	
	public void setRewardfromuserlevel(Byte rewardfromuserlevel){
		this.rewardfromuserlevel=rewardfromuserlevel;
	}
	
	public Integer getReward(){
		return reward;
	}
	
	public void setReward(Integer reward){
		this.reward=reward;
	}
	
	public String rewarddesc(){
		return rewarddesc;
	}
	
	public void setRewarddesc(String rewarddesc){
		this.rewarddesc=rewarddesc;
	}
	
	public Date getUpdatetime(){
		return updatetime;
	}
	
	public void setUpdatetime(Date updatetime){
		this.updatetime=updatetime;
	}
}