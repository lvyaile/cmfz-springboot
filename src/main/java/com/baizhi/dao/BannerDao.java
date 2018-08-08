package com.baizhi.dao;

import com.baizhi.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 白鹿 on 2018/7/31.
 */
public interface BannerDao {
    List<Banner> queryAll(@Param("start")int start, @Param("end") int end);
    void add(Banner banner);
    void delete(int id);
    void update(Banner banner);
    int  getCount();
}
