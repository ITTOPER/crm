package com.bjpowernode.crm.settings.dao;

import com.bjpowernode.crm.settings.domain.User;

import java.util.List;
import java.util.Map;

/**
 * @author LNL
 * @date 2020/7/12 9:20
 */
public interface UserDao {

    /**
     * @param map
     * 登录操作
     * @return
     */
    User login(Map<String, Object> map);

    List<User> getUserList();
}
