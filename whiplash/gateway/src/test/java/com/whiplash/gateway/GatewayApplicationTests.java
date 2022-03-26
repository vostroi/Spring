package com.whiplash.gateway;

import cn.hutool.core.collection.CollUtil;
import com.whiplash.core.commom.util.RedisConstant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@SpringBootTest
class GatewayApplicationTests {

    @Autowired private RedisTemplate<String , Object> redisTemplate;
    @Test
    void contextLoads() {

        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);

        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        redisTemplate.setHashKeySerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);

        Map<String, List<String>> roleResourceMap = new TreeMap<>();
        roleResourceMap.put("/member/hello/test", CollUtil.toList("ADMIN"));
        roleResourceMap.put("/member/usr/curt", CollUtil.toList("ADMIN", "TEST"));

        redisTemplate.opsForHash().putAll("ABC", roleResourceMap);


    }

}
