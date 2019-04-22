package com.qingwei.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by kongwc on 2019/4/9.
 */
@Service
public class RedisArticleService {
    @Autowired
    private RedisTemplate redisTemplate;
    public String postArticle(String title, String content, String link, String userId){
        //文章ID
        String articleId = String.valueOf(redisTemplate.opsForValue().increment("article:"));
        //记录投票的key
        String voted = "voted" + articleId;
        redisTemplate.opsForSet().add(voted, userId);
        //redisTemplate.expire(Constants.one_week);
        return null;
    }
}
