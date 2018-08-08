package com.baizhi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Created by 白鹿 on 2018/8/2.
 */
public class Chapter {
    private int id;
    private String title;
    private String url;
    private int size;
    private String duration;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date cerateDate;
    private int albumId;

    public Chapter() {
    }

    public Chapter(int id, String title, String url, int size, String duration, Date cerateDate, int albumId) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.size = size;
        this.duration = duration;
        this.cerateDate = cerateDate;
        this.albumId = albumId;
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Date getCerateDate() {
        return cerateDate;
    }

    public void setCerateDate(Date cerateDate) {
        this.cerateDate = cerateDate;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", size=" + size +
                ", duration='" + duration + '\'' +
                ", cerateDate=" + cerateDate +
                ", albumId=" + albumId +
                '}';
    }
}
