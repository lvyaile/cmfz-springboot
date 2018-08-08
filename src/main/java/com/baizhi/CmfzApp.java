package com.baizhi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by 白鹿 on 2018/7/30.
 */
@SpringBootApplication
@MapperScan("com.baizhi.dao")
public class CmfzApp {
    public static void main(String[] args) {
        SpringApplication.run(CmfzApp.class, args);
    }
}
