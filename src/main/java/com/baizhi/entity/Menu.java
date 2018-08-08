package com.baizhi.entity;

import java.util.List;

/**
 * Created by 白鹿 on 2018/7/30.
 */
public class Menu {
    private int id;
    private String title;
    private String iconCls;
    private String href;
    private String parentId;
    private List<Menu> children;

    public Menu() {
    }

    public Menu(int id, String title, String iconCls, String href, String parentId, List<Menu> children) {
        this.id = id;
        this.title = title;
        this.iconCls = iconCls;
        this.href = href;
        this.parentId = parentId;
        this.children = children;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", iconCls='" + iconCls + '\'' +
                ", href='" + href + '\'' +
                ", parentId='" + parentId + '\'' +
                ", children=" + children +
                '}';
    }
}
