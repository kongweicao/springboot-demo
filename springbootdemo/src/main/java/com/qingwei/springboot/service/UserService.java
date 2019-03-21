package com.qingwei.springboot.service;

import com.qingwei.springboot.dao.UserMapper;
import com.qingwei.springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kongwc on 2019/3/12.
 */
@Service
@Transactional
public class UserService {
    @Autowired
    private UserMapper userMapper;
    public void insert(User user){
        userMapper.insert(user);
    }
}
