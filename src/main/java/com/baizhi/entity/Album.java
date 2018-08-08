package com.baizhi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

/**
 * Created by 白鹿 on 2018/8/2.
 */
public class Album {
    private int id;
    private String title;
    private String score;
    private String author;
    private String broadcast;
    private int count;
    private String brief;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date publishDate;
    private String coverImg;
    private String status;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date createDate;
    private List<Chapter> children;

    public Album() {
    }

    public Album(int id, String title, String score, String author, String broadcast, int count, String brief, Date publishDate, String coverImg, String status, Date createDate, List<Chapter> children) {
        this.id = id;
        this.title = title;
        this.score = score;
        this.author = author;
        this.broadcast = broadcast;
        this.count = count;
        this.brief = brief;
        this.publishDate = publishDate;
        this.coverImg = coverImg;
        this.status = status;
        this.createDate = createDate;
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

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBroadcast() {
        return broadcast;
    }

    public void setBroadcast(String broadcast) {
        this.broadcast = broadcast;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
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

    public List<Chapter> getChildren() {
        return children;
    }

    public void setChildren(List<Chapter> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", score='" + score + '\'' +
                ", author='" + author + '\'' +
                ", broadcast='" + broadcast + '\'' +
                ", count=" + count +
                ", brief='" + brief + '\'' +
                ", publishDate=" + publishDate +
                ", coverImg='" + coverImg + '\'' +
                ", status='" + status + '\'' +
                ", createDate=" + createDate +
                ", children=" + children +
                '}';
    }
}
