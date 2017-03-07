package com.jeeplus.weixin.entities;

/**
 * Created by 余智平 on 2017/3/7.
 */
public class BaseModel {

    /**
     * 总数
     */
    private int totalNo;

    /**
     * 总页数
     */
    private int pageNo;

    /**
     * 当前分页显示数
     */
    private int pageSize=15;

    /**
     * 当前页数
     */
    private int curPage=0;

    private int startPage;

    /**
     * 排序内容
     */
    private String sortBy;

    /**
     *排序方式
     */
    private String orderBy;


    public int getTotalNo() {
        return totalNo;
    }

    public void setTotalNo(int totalNo) {
        this.totalNo = totalNo;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public int getStartPage() {
        if(startPage < 0){startPage=0;}
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage - 1;//数据库从0行开始算
    }

}
