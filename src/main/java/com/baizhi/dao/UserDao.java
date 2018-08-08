package com.baizhi.dao;

import com.baizhi.entity.China;
import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 白鹿 on 2018/8/6.
 */
public interface UserDao {
    List<User> queryAll(@Param("start") int start, @Param("end") int end);

    int getCount();

    void update(User user);

    List<User> queryAllUser();

    void add(User user);

    int userNum();

    int userNum2();

    int userNum3();

    List<China> chinaMale();

    List<China> chinaFemale();

}
