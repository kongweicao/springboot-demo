package com.qingwei.springboot.controller;

import com.qingwei.springboot.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kongwc on 2019/3/10.
 */
@RestController
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @GetMapping("Status")
    public String status(){
        logger.debug("健康检查成功!");
        return "success";
    }
    @PostMapping("AddUser")
    public String addUser(@RequestBody User user){
        return "success";
    }

    @GetMapping("User")
    public String getUser(User user){
        return "success";
    }
}
