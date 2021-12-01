package com.whiplash.product.components.config;

import com.whiplash.product.components.handler.IgnoreExceptionCacheErrorHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.lang.reflect.Method;
import java.time.Duration;

/**
 * @author Administrator
 * @date 2021/12/1 15:04
 * @projectName whiplash
 * @title: RedisConfig
 * @description: TODO
 */
@Slf4j
@Configuration
public class RedisConfig extends CachingConfigurerSupport {
    @Autowired private LettuceConnectionFactory lettuceConnectionFactory;


    /**
     * 作为 Spring Cache 的实现
     * key 生成 策略
     * @return
     */
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return (Object target , Method method , Object... params)->{
            StringBuffer sb = new StringBuffer();
            sb.append(target.getClass().getName());
            sb.append(":").append(method.getName());
            for (Object param : params) {
                sb.append(":").append(param.toString());
            }
            return sb.toString();
        };
    }


    /**
     * 作为 Spring Cache 的实现
     * 缓存 管理
     * @return
     */
    @Bean
    @Override
    public CacheManager cacheManager() {
        RedisCacheManager redisCacheManager = null;
        try {
            redisCacheManager = RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(lettuceConnectionFactory)
                    .cacheDefaults(
                            RedisCacheConfiguration.defaultCacheConfig()
                                .entryTtl(Duration.ofSeconds(60))
                                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.json()))
                    )
                    // 启用事务
                    .transactionAware()
                    .build();
            redisCacheManager.afterPropertiesSet();
            log.info("Redis Cache Init Finished");
        } catch (Exception e) {
            log.error("Redis Cache Init Exception:", e);
        }

        return redisCacheManager;
    }


    /**
     * 自定义 spring cache 异常处理 即 忽略缓存异常
     * 如果 缓存异常 会调用数据库 当然并发很大， 会导致 缓存雪崩问题
     * @return
     */
    @Override
    public CacheErrorHandler errorHandler() {
        return new IgnoreExceptionCacheErrorHandler();
    }

    /**
     * 实现 动态 选择 cacheManager
     * @return
     */
    @Override
    public CacheResolver cacheResolver() {
        // https://blog.csdn.net/sz85850597/article/details/89301331
        return super.cacheResolver();
    }
}



