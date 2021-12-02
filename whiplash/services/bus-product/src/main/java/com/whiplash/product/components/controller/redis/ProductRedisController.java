package com.whiplash.product.components.controller.redis;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Maps;
import com.sun.org.apache.regexp.internal.RE;
import com.whiplash.components.product.bean.Product;
import com.whiplash.components.product.bean.ProductSpecs;
import com.whiplash.service.ProductMobileService;
import com.whiplash.service.ProductSpecsMobileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.convert.RedisData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @date 2021/12/1 15:03
 * @projectName whiplash
 * @title: RedisController
 * @description: 练习 redis
 */
@Slf4j
@RequestMapping(value = "/rds")
@RestController
public class ProductRedisController {
    @Autowired private StringRedisTemplate stringRedisTemplate;
    @Autowired private RedisTemplate<String,String> redisTemplate;
    @Autowired private ProductMobileService pmService;
    @Autowired private ProductSpecsMobileService psmService;

    /**
     * 测试 由 redis 实现 spring 的缓存
     * @param id
     * @return
     */
    @GetMapping(value = "/prod/get/{id}")
    public Object getProduct(@PathVariable("id") Long id) {
        Product prod = pmService.getUseCache(id);
        return prod;
    }

    /**
     * redis 设置 string
     * @param key
     * @param value
     * @return
     */
    @GetMapping(value = "/set/{key}/{value}")
    public Object setValue(@PathVariable("key") String key, @PathVariable("value") String value) {
        stringRedisTemplate.opsForValue().set(key, value, 30, TimeUnit.SECONDS);

        return "SUCCESS";
    }

    @GetMapping(value = "/get/{key}")
    public Object getValue(@PathVariable("key") String key) {
        String value = stringRedisTemplate.opsForValue().get(key);

        return value;
    }


    /**
     * redis hash 操作， 将 bean 转成 map 一次性 putall
     * @param id
     * @return
     */
    @GetMapping(value = "/hash/set/{id}")
    public Object setHash(@PathVariable("id") Long id) {
        // map 作为参数
        HashMap<String, String> map = Maps.newHashMap();
        map.put("id", "3");
        map.put("price", "23333");
        map.put("crt_time", "2021-12-01 14:57:00");
        map.put("specs_name", "M1 Max芯片(10核) 32G 1TB");

        redisTemplate.opsForHash().putAll("PRODUCT:" + map.get("id"), map);
        redisTemplate.expire("PRODUCT:" + map.get("id"), 20000, TimeUnit.SECONDS);

        String crtTime = (String) redisTemplate.opsForHash().get("PRODUCT:" + map.get("id"), "crt_time");
        Map mapGet = redisTemplate.opsForHash().entries("PRODUCT:" + map.get("id"));

        Map<String, Object> result = Maps.newHashMap();
        result.put("crtTimeGetMap", crtTime);
        result.put("getMap", mapGet);


        // 商品
        Product product = pmService.get(id);
        String prodStr = JSONUtil.toJsonStr(product);

        Map productMap = JSONUtil.toBean(prodStr, Map.class);
        redisTemplate.opsForHash().putAll("PRODUCT:"+product.getId(), productMap);

        Map productGet = redisTemplate.opsForHash().entries("PRODUCT:" + product.getId().toString());
        result.put("productGet" , productGet);

        redisTemplate.opsForHash().increment("PRODUCT:" + product.getId().toString(), "sales", 3);


        // 商品规格
        Iterable<ProductSpecs> all = psmService.getDao().findAll();
        if(all!=null){
            all.forEach(ps->{
                String psStr = JSONUtil.toJsonStr(ps);

                Map psMap = JSONUtil.toBean(psStr, Map.class);

                redisTemplate.opsForHash().putAll("PRODUCT:SPECS:" + ps.getId(), psMap);
            });
        }

        return result;
    }

    @GetMapping(value = "/hash/get/{id}")
    public Object getHash(@PathVariable("id") Long id) {

        Map mapGet = redisTemplate.opsForHash().entries("PRODUCT:" + id);
        Map mapSpecsGet = redisTemplate.opsForHash().entries("PRODUCT:SPECS:" + 1);
        Set keys = redisTemplate.keys("*");

        Map<String, Object> result = Maps.newHashMap();
        result.put("mapGet", mapGet);
        result.put("mapSpecsGet", mapSpecsGet);
        result.put("keys", keys);

        return result;
    }

    /**
     * redis list 操作 粉丝列表  消息列表  文章列表
     * @return
     */
    @GetMapping(value = "/list/set")
    public Object setList() {
        redisTemplate.opsForList().leftPush("FANS:1", "2");
        redisTemplate.opsForList().leftPush("FANS:1", "3");
        redisTemplate.opsForList().leftPush("FANS:1", "4");
        redisTemplate.opsForList().leftPush("FANS:1", "5");
        redisTemplate.opsForList().leftPush("FANS:1", "6");
        redisTemplate.opsForList().leftPush("FANS:2", "3");
        redisTemplate.opsForList().leftPush("FANS:2", "5");
        redisTemplate.opsForList().leftPush("FANS:2", "7");
        redisTemplate.opsForList().leftPush("FANS:2", "8");

        redisTemplate.opsForList().leftPushAll("FANS:3", Arrays.asList("1","7","4","2","9"));

        // 全部
        List<String> fans1 = redisTemplate.opsForList().range("FANS:1", 0, -1);

        Map<String, Object> result = Maps.newHashMap();

        result.put("fans1" , fans1);
        result.put("KEYS" , redisTemplate.keys("*"));

        return result;
    }

    /**
     * redis set 操作
     * 求交集  并集 差集 等 操作， 非常方便实现 共同好友，共同关注等...
     * @return
     */
    @GetMapping(value = "/set/set")
    public Object setSet() {
        redisTemplate.opsForSet().add("FAVOR:1", "1","7","4","2","9");
        redisTemplate.opsForSet().add("FAVOR:4", "2", "6","9");
        redisTemplate.opsForSet().add("FAVOR:2", "1", "6","3","5","8");
        redisTemplate.opsForSet().add("FAVOR:3", "1", "2","3","4","5");

        Set<String> dif_1_2 = redisTemplate.opsForSet().difference("FAVOR:1", "FAVOR:2");
        Long dif_1_2_store = redisTemplate.opsForSet().differenceAndStore("FAVOR:1", "FAVOR:2", "FAVOR:7");

        Set<String> inter_1_2 = redisTemplate.opsForSet().intersect("FAVOR:1", "FAVOR:2");
        Long inter_1_2_store = redisTemplate.opsForSet().intersectAndStore("FAVOR:1", "FAVOR:2", "FAVOR:9");

        HashMap<String, Object> result = Maps.newHashMap();
        result.put("dif_1_2", dif_1_2);
        result.put("dif_1_2_store", dif_1_2_store);
        result.put("inter_1_2", inter_1_2);
        result.put("inter_1_2_store", inter_1_2_store);

        return result;
    }

    /**
     * redis sortedSet 可用于排行榜等
     * sortedSet 内部 使用 HashMap 和跳跃表 SkipList 来存储数据 保证有序。 HashMap存放的是成员到score的映射，SkipList按从小到大保存所有集合元素。
     * 使用SkipList可以获得比较高的查询效率，实现也相对简单
     * @return
     */
    @GetMapping(value = "/zset/set")
    public Object sortedSet() {
        redisTemplate.opsForZSet().add("MUSIC", "夜曲",90);
        redisTemplate.opsForZSet().add("MUSIC", "七里香", 69);
        redisTemplate.opsForZSet().add("MUSIC", "龙拳", 35);
        redisTemplate.opsForZSet().add("MUSIC", "黑色毛衣", 92);

        Set<String> musicAsc = redisTemplate.opsForZSet().range("MUSIC", 0, -1);
        Set<String> musicDesc = redisTemplate.opsForZSet().reverseRange("MUSIC", 0, -1);

        Map<String, Object> result = Maps.newHashMap();
        result.put("musicAsc", musicAsc);
        result.put("musicDesc", musicDesc);
        return  result;
    }


}
