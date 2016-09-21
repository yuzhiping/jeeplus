package com.jeeplus.admin.common.entities;

/**
 * ztree.js的树节点类
 * Created by yuzp17311 on 2016/8/24.
 */
public class zTreeNode {
    private int id; //id
    private int pId; //父节点Id
    private String name; //名称
    private boolean open=false; //是否打开
    private String iconOpen; //节点打开时的图标路径
    private String iconClose; //节点关闭时的图标路径
    private String icon; //图标路径
    private boolean nChecked; //节点是否选中
    private String url; //
    private String target; //点击打开，与url配合
    private String tip;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getIconOpen() {
        return iconOpen;
    }

    public void setIconOpen(String iconOpen) {
        this.iconOpen = iconOpen;
    }

    public String getIconClose() {
        return iconClose;
    }

    public void setIconClose(String iconClose) {
        this.iconClose = iconClose;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean isnChecked() {
        return nChecked;
    }

    public void setnChecked(boolean nChecked) {
        this.nChecked = nChecked;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}
