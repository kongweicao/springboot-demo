package com.qingwei.springboot.dao;


import com.qingwei.springboot.model.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    List<User> getUser(User user);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}