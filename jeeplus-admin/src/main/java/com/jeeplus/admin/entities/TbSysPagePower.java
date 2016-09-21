package com.jeeplus.admin.entities;



public class TbSysPagePower {
    private Integer id;

    private String menuName;

    private String menuUrl;

    private Integer parentId;

    private Integer level;

    private String type;

    private String iconStr;

    private Integer isSort;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl == null ? null : menuUrl.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getIconStr() {
        return iconStr;
    }

    public void setIconStr(String iconStr) {
        this.iconStr = iconStr == null ? null : iconStr.trim();
    }

    public Integer getIsSort() {
        return isSort;
    }

    public void setIsSort(Integer isSort) {
        this.isSort = isSort;
    }
}