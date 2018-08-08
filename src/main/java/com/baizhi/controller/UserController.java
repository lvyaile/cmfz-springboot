package com.baizhi.controller;

import com.alibaba.fastjson.JSONObject;
import com.baizhi.entity.China;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 白鹿 on 2018/8/6.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/queryAll")
    protected @ResponseBody
    Map queryAll(int page, int rows) {
        Map map = userService.queryAll(page, rows);
        return map;
    }

    @RequestMapping("/update")
    public @ResponseBody
    Map update(User user) {
        Map map = new HashMap();
        try {
            userService.update(user);
            map.put("isUpdate", "修改成功");
        } catch (Exception e) {
            map.put("isUpdate", "修改失败了");
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping("/export")
    public @ResponseBody
    void export(HttpServletResponse response) {
        userService.export(response);
    }

    @RequestMapping("/userImpl")
    public @ResponseBody
    void userImpl(MultipartFile file) {
        userService.userImport(file);
    }

    @RequestMapping("/customerExport")
    public @ResponseBody
    void customerExport(String title, String fileds, HttpServletResponse response) {
        userService.customerExport(title, fileds, response);
    }

    @RequestMapping("/num")
    public @ResponseBody
    List<Integer> userNum(HttpSession session) {
        int num = userService.userNum();
        int num2 = userService.userNum2();
        int num3 = userService.userNum3();
        List<Integer> integers = new ArrayList<>();
        integers.add(num);
        integers.add(num2);
        integers.add(num3);
        System.out.println(integers);
        return integers;
    }

    @RequestMapping("/china")
    public @ResponseBody
    JSONObject china(HttpSession session) {
        JSONObject jsonObject = new JSONObject();
        List<JSONObject> male = new ArrayList<>();
        JSONObject jsonObject1 = null;
        List<China> chinaMale = userService.chinaMale();
        for (China china : chinaMale) {
            System.out.println(china.getLocation() + "********" + china.getCount());
            jsonObject1 = new JSONObject();
            jsonObject1.put("name", china.getLocation());
            jsonObject1.put("value", china.getCount());
            male.add(jsonObject1);
        }

        System.out.println(male);
        List<JSONObject> female = new ArrayList<>();
        JSONObject jsonObject2 = null;
        List<China> chinaFemale = userService.chinaFemale();
        for (China china : chinaFemale) {
            jsonObject2 = new JSONObject();
            jsonObject2.put("name", china.getLocation());
            jsonObject2.put("value", china.getCount());
            female.add(jsonObject2);
        }

        jsonObject.put("male", male);
        jsonObject.put("female", female);
        System.out.println(jsonObject);
        return jsonObject;
    }

}
