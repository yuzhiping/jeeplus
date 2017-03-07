package com.jeeplus.weixin.entities;

import java.util.Date;

public class UserInfoModel extends BaseModel {
	 
    private Integer recordid;

    private String phoneno;

    private String pwd;

    private String refferno;

    private String username;

    private Byte gender;

    private String idcard;

    private String email;

    private Byte userlevel;

    private Date updatetime;

    private String userpic;
    
    private String openid;
    
    public Integer getRecordid() {
        return recordid;
    }

    public void setRecordid(Integer recordid) {
        this.recordid = recordid;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRefferno() {
        return refferno;
    }

    public void setRefferno(String refferno) {
        this.refferno = refferno;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Byte getUserlevel() {
        return userlevel;
    }

    public void setUserlevel(Byte userlevel) {
        this.userlevel = userlevel;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

	public String getUserpic() {
		return userpic;
	}

	public void setUserpic(String userpic) {
		this.userpic = userpic;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
 
    
}