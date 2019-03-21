package com.qingwei.springboot;

import com.qingwei.springboot.model.User;
import com.qingwei.springboot.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Created by kongwc on 2019/3/12.
 */
@SpringBootTest(classes = BootApplication.class)
@RunWith(SpringRunner.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Test
    public void addTest(){
        User user = new User();
        user.setAge(1);
        user.setCreateDate(new Date());
        user.setUserName("鸣人");
        user.setEmail("mingren@126.com");
        userService.insert(user);
    }
}
