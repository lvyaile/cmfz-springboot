package com.baizhi.service;

import com.baizhi.entity.Admin;

/**
 * Created by 白鹿 on 2018/7/30.
 */
public interface AdminService {
    Admin query(String username, String password);
}
