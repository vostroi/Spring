package com.whiplash.product.components.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;

/**
 * @author Administrator
 * @date 2021/12/1 22:55
 * @projectName whiplash
 * @title: IgnoreExceptionCacheErrorHandler
 * @description: 处理 当使用 spring 缓存异常 导致业务终止或异常的情况
 * 忽略 spring 缓存 产生的异常
 */
@Slf4j
public class IgnoreExceptionCacheErrorHandler implements CacheErrorHandler {
    @Override
    public void handleCacheGetError(RuntimeException e, Cache cache, Object o) {
        log.error("handleCacheGetError 异常", e);
    }

    /**
     * 处理 当缓存异常时，缓存数据 和 DB 中数据不一致情况
     * @param e
     * @param cache
     * @param o
     * @param o1
     */
    @Override
    public void handleCachePutError(RuntimeException e, Cache cache, Object o, Object o1) {
        log.error("handleCachePutError 异常", e);

    }

    /**
     * 处理 当缓存异常时，缓存数据 和 DB 中数据不一致情况
     * 思路：将写操作的key保存下来，通过重试任务删除这些key 解决与 DB 中不一致的问题
     * @param e
     * @param cache
     * @param o
     */
    @Override
    public void handleCacheEvictError(RuntimeException e, Cache cache, Object o) {
        log.error("handleCacheEvictError 异常", e);
    }

    @Override
    public void handleCacheClearError(RuntimeException e, Cache cache) {
        log.error("handleCacheClearError 异常", e);
    }
}
