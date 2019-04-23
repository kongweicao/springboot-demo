package com.qingwei.springboot.service;

import com.qingwei.springboot.constant.Constants;
import com.qingwei.springboot.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by kongwc on 2019/4/9.
 */
@Service
public class RedisArticleService {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 发布文章
     * @param title
     * @param content
     * @param link
     * @param userId
     * @return
     */
    public String postArticle(String title, String content, String link, String userId){
        //文章ID
        Long id = redisTemplate.opsForValue().increment("article:");
        //记录投票的key
        String voted = "voted:" + id;
        redisTemplate.opsForSet().add(voted, userId);
        redisTemplate.expire(voted, 7, TimeUnit.DAYS);

        String articleId = "article:" + id;
        HashMap<String, String> map = new HashMap<>();
        map.put("title", title);
        map.put("content", content);
        map.put("link", link);
        map.put("postDate", DateUtil.getDateTimeStr(new Date()));
        map.put("votes", "1");
        redisTemplate.opsForHash().putAll(articleId, map);
        //添加分数用于排序
        redisTemplate.opsForZSet().add("score:info", articleId, 0);
        //添加发布日期，用于排序
        Long now = new Date().getTime();
        redisTemplate.opsForZSet().add("date:info", articleId, now);
        return articleId;
    }

    /**
     * 文章投票
     * @param userId
     * @param articleId
     */
    public void voteArticle(String userId, String articleId){
        //判断是否过期 一周以后不能投票
        long cutoff = System.currentTimeMillis()/1000 - Constants.ONE_WEEK_IN_SECONDS;
        Double score = redisTemplate.opsForZSet().score("date:info", articleId);
        if(score < cutoff){
            return;
        }
        //获取文章主键id
        String articleIndex = articleId.substring(articleId.indexOf(':') + 1); ////article:1
        //如果没投过票
        if(redisTemplate.opsForSet().add("voted:" + articleIndex, userId) == 1){
            redisTemplate.opsForZSet().incrementScore("score:info", articleId, 100);
            redisTemplate.opsForHash().increment(articleId, "voted", 1);
        }
    }

    /**
     * 查询出所有文章
     * @param page
     * @param key
     * @return
     */
    public List<Map<String, String>> getArticles(int page, String key) {
        int start = (page - 1) * Constants.ARTICLES_PER_PAGE;
        int end = start + Constants.ARTICLES_PER_PAGE - 1;
        //倒序查询出投票数最高的文章，zset有序集合，分值递减
        Set<String> ids = redisTemplate.opsForZSet().range(key, start, end);
        List<Map<String,String>> articles = new ArrayList<Map<String,String>>();
        for (String id : ids){
            Map<String,String> articleData = redisTemplate.opsForHash().entries(id);
            articleData.put("id", id);
            articles.add(articleData);
        }
        return articles;
    }
}
