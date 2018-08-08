package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 白鹿 on 2018/8/1.
 */
@RestController
@RequestMapping("/banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @RequestMapping("/queryAll")
    public Map queryAll(int page, int rows) {
        System.out.println(page + "aaaaa" + rows);
        Map map = bannerService.queryAll(page, rows);
        return map;
    }

    @RequestMapping("/add")
    public Map add(String title, String status, String description, MultipartFile img, HttpSession session) {
        System.out.println(title + "qqq" + status + "qqq" + description + "qqq" + img);
        Map map = new HashMap();
        //不在service层做异常处理
        try {
            bannerService.add(title, status, description, img, session);
            map.put("isAdd", "添加成功");
        } catch (Exception e) {
            map.put("isAdd", "添加失败");
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping("/delete")
    public Map delete(int id) {
        System.out.println(id);
        Map map = new HashMap();
        try {
            bannerService.delete(id);
            map.put("isDelete", "删除成功");
        } catch (Exception e) {
            map.put("isDelete", "删除失败");
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping("/update")
    public Map update(Banner banner) {
        System.out.println("111");
        System.out.println(banner);
        System.out.println("aigengpushan");
        Map map = new HashMap();
        try {
            bannerService.update(banner);
            map.put("isUpdate", "修改成功");
        } catch (Exception e) {
            map.put("isUpdate", "修改失败");
            e.printStackTrace();
        }
        return map;
    }
}
