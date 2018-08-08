package com.baizhi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 白鹿 on 2018/7/30.
 */
@Controller
@RequestMapping("/test")
public class Test {
    @RequestMapping("/test")
    public String Test() {
        return "index";
    }
}
