package com.baizhi.service;

import com.baizhi.entity.Chapter;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

/**
 * Created by 白鹿 on 2018/8/3.
 */
public interface ChapterService {
    void add(Chapter chapter, MultipartFile img, HttpSession session);
}
