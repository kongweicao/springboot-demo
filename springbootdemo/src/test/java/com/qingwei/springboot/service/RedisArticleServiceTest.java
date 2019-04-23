package com.qingwei.springboot.service;

import com.alibaba.fastjson.JSON;
import com.qingwei.springboot.BootApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by kongwc on 2019/4/23.
 */
@SpringBootTest(classes = {BootApplication.class})
@RunWith(SpringRunner.class)
public class RedisArticleServiceTest {
    @Resource
    private RedisArticleService redisArticleService;
    @Test
    public void postArticleTest() throws Exception {
        String title = "春天来了";
        String content = "春天来了，花儿开了，小可乐长大了";
        String  link = "www.baidu.com";
        String userId = "U001";
        redisArticleService.postArticle(title, content, link, userId);
    }
    @Test
    public void voteArticleTest() throws Exception {
        String userId = "U002";
        String articleId = "article:1";
        redisArticleService.voteArticle(userId, articleId);
    }
    @Test
    public void getArticlesTest() throws Exception {
        List<Map<String, String>> mapList = redisArticleService.getArticles(1, "score:info");
        System.out.println(JSON.toJSONString(mapList));
    }
}