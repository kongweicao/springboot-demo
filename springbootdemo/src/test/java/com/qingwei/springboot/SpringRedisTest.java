package com.qingwei.springboot;

/**
 * Created by kongwc on 2019/3/16.
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@SpringBootTest(classes = {BootApplication.class})
@RunWith(SpringRunner.class)
public class SpringRedisTest {

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @Test
    public void testRedis() throws Exception {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set("name", "kongwc1");
        ops.set("name", "kongwc1", 10, TimeUnit.SECONDS); //10秒失效
        String value = ops.get("name");
        System.out.println(value);
    }

    /**
     * 批量插入字符串
     * @throws Exception
     */
    @Test
    public void msetTest() throws Exception {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        Map map = new HashMap<>();
        map.put("name", "bainiu");
        map.put("age", 12);
        ops.multiSet(map);
        ops.multiSetIfAbsent(map);//如果不存在则插入
        //自增
        ops.increment("age");
        String value = ops.get("age");
        System.out.println(value);
    }

    @Test
    public void mMapTest() throws Exception {
        HashOperations ops = redisTemplate.opsForHash();
        Map map = new HashMap<>();
        map.put("name", "bainiu");
        map.put("age", 12);
        ops.put("userId1", "name", "kongwc");
        Map map1 = new HashMap<>();
        map1.put("name", "mori");
        map1.put("age", 123);
        ops.putAll("userId2", map1);
        List<Object> hashList = ops.values("userId2"); //[mori, 123]
        Map mapResult = ops.entries("userId2"); //{name=mori, age=123}
        String name = (String) ops.get("userId2", "name"); //mori
        System.out.println(name);
    }

    @Test
    public void ListTest() throws Exception {
        ListOperations ops = redisTemplate.opsForList();
        ops.leftPush("kong","kaer");
        ops.leftPush("kong","moning");
        ops.leftPush("kong","dick");
        System.out.println(ops.range("kong", 0, -1)); //[dick, moning, kaer]
        ops.leftPush("kong","moning", "mama");
        System.out.println(ops.range("kong", 0, -1)); //[dick, mama, moning, kaer]
        ops.leftPushAll("kong", Arrays.asList("c", "b", "a")); // [a, b, c]
        System.out.println(ops.leftPop("kong")); //从左边移除第一个元素 [dick]
    }

    @Test
    public void setTest() throws Exception {
        SetOperations ops = redisTemplate.opsForSet();
        ops.add("wei", "a", "b", "c");
        System.out.println(ops.members("wei")); //[a, c, b]
    }

    /**
     * 全局查询
     * @throws Exception
     */
    @Test
    public void globalTest() throws Exception {
        System.out.println(redisTemplate.keys("*")); //查询所有的建
        System.out.println(redisTemplate.keys("k*")); //以k开头的
        System.out.println(redisTemplate.keys("*").size());
        System.out.println(redisTemplate.hasKey("kong")); //查询key是否存在
        //System.out.println(redisTemplate.delete("kong")); //删除key
        System.out.println(redisTemplate.expire("kong", 10, TimeUnit.DAYS)); //设置key的失效时间
        System.out.println(redisTemplate.getExpire("kong")); //健剩余的过期时间
        System.out.println(redisTemplate.type("kong")); //健的返回类型，不存在返回none
    }
}

