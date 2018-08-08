package com.baizhi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Created by 白鹿 on 2018/7/31.
 */
public class Banner {
    private int id;
    private String title;
    private String url;
    private String status;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date createDate;
    private String description;

    public Banner() {
    }

    public Banner(int id, String title, String url, String status, Date createDate, String description) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.status = status;
        this.createDate = createDate;
        this.description = description;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "banner{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", status='" + status + '\'' +
                ", createDate='" + createDate + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
