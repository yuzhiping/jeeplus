package com.jeeplus.weixin.entities;

public class DictModel {
    private Integer id;

    private String name;

    private String value;

    private String sqls;

    private Integer type;

    private String category;

    private Integer displayseq;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

   

    public String getSqls() {
		return sqls;
	}

	public void setSqls(String sqls) {
		this.sqls = sqls;
	}

	public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

 
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getDisplayseq() {
		return displayseq;
	}

	public void setDisplayseq(Integer displayseq) {
		this.displayseq = displayseq;
	}

   
}