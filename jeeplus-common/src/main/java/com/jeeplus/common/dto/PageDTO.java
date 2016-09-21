package com.jeeplus.common.dto;

import java.util.List;

/**
 * Created by yuzp17311 on 2016/9/2.
 */
public class PageDTO<T> {
    private Integer startRow; // 记录开始条数
    private Integer pageSize; // 每页记录条数
    private int rowCount; // 总记录
    private List<T> rows;

    public PageDTO() {

    }

    public PageDTO(int startRow, int pageSize) {
        this.startRow = startRow;
        this.pageSize = pageSize;
    }

    public Integer getStartRow() {
        return startRow;
    }

    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public void copy(PageDTO<T> tmp) {
        this.startRow = tmp.startRow;
        this.pageSize = tmp.pageSize;
        this.rowCount = tmp.rowCount;
        this.rows = tmp.rows;
    }

}
