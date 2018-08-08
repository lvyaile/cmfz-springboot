package com.baizhi.service;

import com.baizhi.entity.Banner;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by 白鹿 on 2018/8/1.
 */
public interface BannerService {
    Map queryAll(int page, int rows);
    void  add(String title, String status, String description, MultipartFile img, HttpSession session);
    void  delete(int id);
    void  update(Banner banner);
}
