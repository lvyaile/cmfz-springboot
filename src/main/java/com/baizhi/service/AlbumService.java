package com.baizhi.service;

import com.baizhi.entity.Album;
import com.baizhi.entity.Albums;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by 白鹿 on 2018/8/2.
 */
public interface AlbumService {
    Map queryAlbum(int page, int rows);
    Album queryOne(int id);
    void add(Album album, MultipartFile img, HttpSession session);
}
