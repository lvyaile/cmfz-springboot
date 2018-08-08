package com.baizhi.service;

import com.baizhi.entity.China;
import com.baizhi.entity.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by 白鹿 on 2018/8/6.
 */
public interface UserService {
    Map queryAll(int page, int rows);
    void update(User user);
    void export(HttpServletResponse response);
    public void userImport(MultipartFile file);
    public void customerExport(String title, String fileds, HttpServletResponse response);
    int userNum();
    int userNum2();
    int userNum3();
    List<China> chinaMale();
    List<China> chinaFemale();
}
