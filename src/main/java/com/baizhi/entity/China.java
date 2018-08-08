package com.baizhi.entity;

/**
 * Created by 白鹿 on 2018/8/6.
 */
public class China {
    private String location;
    private int count;

    public China() {
    }

    public China(String location, int count) {
        this.location = location;
        this.count = count;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "China{" +
                "location='" + location + '\'' +
                ", count=" + count +
                '}';
    }
}
