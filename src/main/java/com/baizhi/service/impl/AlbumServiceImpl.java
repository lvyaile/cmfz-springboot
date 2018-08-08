package com.baizhi.service.impl;

import com.baizhi.dao.AlbumDao;
import com.baizhi.entity.Album;
import com.baizhi.entity.Albums;
import com.baizhi.service.AlbumService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by 白鹿 on 2018/8/2.
 */
@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumDao albumDao;

    @Override
    public Map queryAlbum(int page, int rows) {
        int start = (page - 1) * rows;
        int end = rows;
        List<Album> list = albumDao.queryAlbum(start, end);
        int total = albumDao.getCount();
        Map map = new HashMap();
        map.put("rows", list);
        map.put("total", total);
        return map;
    }

    @Override
    public Album queryOne(int id) {

        Album album = albumDao.queryOne(id);
        return album;
    }

    public void add(Album album, MultipartFile img, HttpSession session) {

        String projectPath = session.getServletContext().getRealPath("/");
        String uploadPath = projectPath + "upload";
        File file = new File(uploadPath);
        if (!file.exists()) {
            file.mkdir();
        }
        String originalFilename = img.getOriginalFilename();
        String extension = FilenameUtils.getExtension(originalFilename);
        String uuid = UUID.randomUUID().toString();
        String newName = uuid + "." + extension;
        try {
            img.transferTo(new File(uploadPath, newName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        album.setCoverImg(newName);
        System.out.println(album);
        albumDao.add(album);
    }
}
