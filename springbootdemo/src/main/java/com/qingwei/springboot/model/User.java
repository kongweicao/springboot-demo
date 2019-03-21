package com.qingwei.springboot.model;

import lombok.Data;

import java.util.Date;

/**
 * Created by kongwc on 2019/3/12.
 */
@Data
public class User {
    private int id;
    private String userName;
    private String password;
    private String email;
    private Integer age;
    private String gendar;
    private String job;
    private Date createDate;
    private String createUser;
}
