package com.baizhi.dao;

import com.baizhi.entity.Album;
import com.baizhi.entity.Albums;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 白鹿 on 2018/8/2.
 */
public interface AlbumDao {
    List<Album> queryAlbum(@Param("start") int start, @Param("end") int end);

    Album queryOne(int id);

    void add(Album album);

    int getCount();
}
