package com.baizhi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2018/8/5.
 */
public class Logging implements Serializable{
    private int id;
    private String username;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:dd")
    private Date time;
    private String name;
    private boolean flag;

    @Override
    public String toString() {
        return "Logging{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", time=" + time +
                ", name='" + name + '\'' +
                ", flag=" + flag +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Logging(String username, Date time, String name, boolean flag) {

        this.username = username;
        this.time = time;
        this.name = name;
        this.flag = flag;
    }

    public Logging() {
    }
}
