package com.baizhi.service.impl;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 白鹿 on 2018/7/30.
 */
@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Override
    public Admin query(String username, String password) {
        Admin admin = adminDao.query(username);
        System.out.println(username);
        System.out.println(admin);
        if (admin == null) {
            throw new RuntimeException("该用户不存在");
        } else {
            if (!admin.getPassword().equals(password)) {
                throw new RuntimeException("对不起，密码错误");
            } else {
                return admin;
            }
        }
        }
    }
