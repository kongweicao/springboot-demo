package com.qingwei.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kongwc on 2019/3/13.
 */
@RestController
public class BaseController {
    @GetMapping("/404.do")
    public String notFound(){
        return "你要的路径找不到了哦，换一下试试呢！";
    }
}
