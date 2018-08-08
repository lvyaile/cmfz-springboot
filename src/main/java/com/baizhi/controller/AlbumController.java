package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.entity.Albums;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 白鹿 on 2018/8/2.
 */
@RestController
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @RequestMapping("/queryAlbum")
    @ResponseBody
    public Map queryAlbum(int page, int rows) {
        System.out.println(page + "aaaaa" + rows);

        Map map = albumService.queryAlbum(page, rows);
        System.out.println(map);
        return map;
    }
    @RequestMapping("/queryOne")
    public Album queryOne(int id){
        System.out.println(id);
        Album album= albumService.queryOne(id);
        System.out.println(album);
        return album;
    }
    @RequestMapping("/add")
    public Map add(Album album, MultipartFile img, HttpSession session){
        System.out.println(album);
        Map map = new HashMap();
        //不在service层做异常处理
        try {
            albumService.add(album,img,session);
            map.put("isAdd", "添加成功");
        } catch (Exception e) {
            map.put("isAdd", "添加失败");
            e.printStackTrace();
        }
        return map;
    }
}
