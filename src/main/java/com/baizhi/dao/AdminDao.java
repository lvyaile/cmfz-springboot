package com.baizhi.dao;

import com.baizhi.entity.Admin;

/**
 * Created by 白鹿 on 2018/7/30.
 */
public interface AdminDao {
    Admin query(String username);
}
