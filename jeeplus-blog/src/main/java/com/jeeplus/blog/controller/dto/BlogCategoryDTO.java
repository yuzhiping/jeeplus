package com.jeeplus.blog.controller.dto;

/**
 * @author:yuzp17311
 * @version:v1.0
 * @date: 2017-02-23 19:45.
 */
public class BlogCategoryDTO {

    private String id;
    private String categoryName;
    private String categoryRemark;
    private Integer categoryPosition;
    private Integer total;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryRemark() {
        return categoryRemark;
    }

    public void setCategoryRemark(String categoryRemark) {
        this.categoryRemark = categoryRemark;
    }

    public Integer getCategoryPosition() {
        return categoryPosition;
    }

    public void setCategoryPosition(Integer categoryPosition) {
        this.categoryPosition = categoryPosition;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "BlogCategoryDTO{" +
                "id='" + id + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", categoryRemark='" + categoryRemark + '\'' +
                ", categoryPosition=" + categoryPosition +
                ", total=" + total +
                '}';
    }
}
