package com.baizhi.service.impl;

import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
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
 * Created by 白鹿 on 2018/8/1.
 */
@Service
@Transactional
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerDao bannerDao;


    @Override
    public Map queryAll(int page, int rows) {
        int start = (page - 1) * rows;
        int end = rows;
        System.out.println(start);
        List<Banner> list = bannerDao.queryAll(start, end);
        int total = bannerDao.getCount();
        Map map = new HashMap();
        map.put("rows", list);
        map.put("total", total);
        return map;
    }

    @Override
    public void add(String title, String status, String description, MultipartFile img, HttpSession session) {
        //获取文件
        //文件保存
        String projectPath = session.getServletContext().getRealPath("/");
        String uploadPath = projectPath + "upload";
        File file = new File(uploadPath);
        if (!file.exists()) {
            file.mkdir();
        }
        //3文件名
        String originalFilename = img.getOriginalFilename();
        String extension = FilenameUtils.getExtension(originalFilename);
        String uuid = UUID.randomUUID().toString();
        String newName = uuid + "." + extension;
        try {
            img.transferTo(new File(uploadPath, newName));
        } catch (IOException e) {
            e.printStackTrace();
        };
        String url=newName;
        Date date=new Date();
        Banner banner=new Banner(0,title,url,status,null,description);
        System.out.println(banner);
        bannerDao.add(banner);
    }


    @Override
    public void delete(int id) {
        bannerDao.delete(id);
    }

    @Override
    public void update(Banner banner) {
        bannerDao.update(banner);
    }
}
