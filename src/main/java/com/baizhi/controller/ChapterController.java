package com.baizhi.controller;

import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 白鹿 on 2018/8/3.
 */
@RestController
@RequestMapping("/chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    @RequestMapping("/addChapter")
    public Map addChapter(Chapter chapter, MultipartFile audio, HttpSession session) {
        System.out.println(chapter);
        Map map = new HashMap();
        try {
            chapterService.add(chapter,audio,session);
            map.put("isAdd", "添加成功");
        }catch (Exception e){
            map.put("isAdd", "添加失败");
            e.printStackTrace();
            System.out.println("1");
        }
        return map;
    }
    @RequestMapping("/down")
    public void down(String url, String name, HttpSession session, HttpServletResponse response){
        String realPath = session.getServletContext().getRealPath("/");
        String uploadDir = realPath + "audio";
        String filePath = uploadDir + "/" + url;
        File file = new File(filePath);
        String extension = FilenameUtils.getExtension(url);
        name=name+"."+extension;
        String newName=null;
        try {
            //新文件名
            newName = new String(name.getBytes("utf-8"), "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //设置相应类型
        response.setContentType("audio/mpeg");
        //设置响应头文件
        response.setHeader("content-Disposition", "attachment;fileName=" + newName);
        try {
            //获取输出流
            ServletOutputStream outputStream = response.getOutputStream();
            //用输出流写文件
            outputStream.write(FileUtils.readFileToByteArray(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

