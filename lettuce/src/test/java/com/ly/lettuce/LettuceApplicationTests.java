package com.ly.lettuce;

import com.ly.lettuce.entity.User;
import com.ly.lettuce.utils.RedisCacheConstants;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@SpringBootTest(classes = LettuceApplication.class)
@Slf4j
class LettuceApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;


    @Test
    void contextLoads() {
        log.info("redisTemplate={}",redisTemplate.getValueSerializer());
        log.info("redisTemplate={}",redisTemplate.getKeySerializer());

    }

    @Test
    void testRedisSave() {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setAge(12);
        user.setCreateTime(new Date());
        user.setVersion(123456L);
        user.setAmount(new BigDecimal("123.4567"));
        user.setScore(null);

        redisTemplate.opsForValue().set(RedisCacheConstants.USER_DB_STRING + ":"+"test","testValue",60, TimeUnit.SECONDS);
        redisTemplate.opsForValue().set(RedisCacheConstants.USER_DB_STRING + ":" + user.getId() , user , 100,TimeUnit.SECONDS);
    }

    @Test
    void testRedisGet() {
        User user = (User) redisTemplate.opsForValue().get("USER_STRING:126d2d87-73fc-42d3-ad4c-0f2efbcb2985");
        log.info("user={}",user);
        String testValue = (String)redisTemplate.opsForValue().get("test");
        log.info("testValue={}",testValue);

    }



}
