package com.baizhi.service.impl;

import com.baizhi.dao.ChapterDao;
import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by 白鹿 on 2018/8/3.
 */
@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterDao chapterDao;
    @Override
    public void add(Chapter chapter, MultipartFile audio, HttpSession session) {
        String projectPath = session.getServletContext().getRealPath("/");
        String audioDir = projectPath + "audio";;
        File file=new File(audioDir);
        if (!file.exists()){
            file.mkdir();
        }
        String originalFilename = audio.getOriginalFilename();;
        String extension = FilenameUtils.getExtension(originalFilename);;
        String uuid = UUID.randomUUID().toString();
        String newName = uuid + "." + extension;
        try {
            audio.transferTo(new File(audioDir, newName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        File file1=new File(newName);
        if (file1.exists()&&file1.isFile()){
            String fileName=file1.getName();
            System.out.println(fileName);
            System.out.println(file1.length());
        }
        chapter.setUrl(newName);
        chapter.setDuration("4.12");
        chapter.setSize((int) file1.length());
        chapterDao.add(chapter);
    }
}
