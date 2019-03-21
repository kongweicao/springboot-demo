package com.qingwei.springboot;

/**
 * Created by kongwc on 2019/3/16.
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@SpringBootTest(classes = {BootApplication.class})
@RunWith(SpringRunner.class)
public class SpringRedisTest {

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @Test
    public void testRedis() throws Exception {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set("name", "kongwc");
        String value = ops.get("name");
        System.out.println(value);
    }
}

