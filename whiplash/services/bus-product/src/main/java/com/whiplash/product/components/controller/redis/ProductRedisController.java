package com.whiplash.product.components.controller.redis;

import com.whiplash.components.product.bean.Product;
import com.whiplash.service.ProductMobileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @Autowired private ProductMobileService pmService;

    @GetMapping(value = "/set/{key}/{value}")
    public Object setValue(@PathVariable("key") String key, @PathVariable("value") String value) {
        stringRedisTemplate.opsForValue().set(key, value, 2000);

        return "SUCCESS";
    }

    @GetMapping(value = "/get/{key}")
    public Object getValue(@PathVariable("key") String key) {
        String value = stringRedisTemplate.opsForValue().get(key);

        return value;
    }

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


}
