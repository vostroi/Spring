package com.whiplash.oauth.components.service;

import cn.hutool.core.collection.CollUtil;
import com.whiplash.core.commom.util.RedisConstant;
import com.whiplash.oauth.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Administrator
 * @date 2021/9/19 14:31
 * @projectName whiplash
 * @title: ResourceServiceImpl
 * @description: 资源 与 角色 匹配关系 业务
 */
@Service
public class ResourceServiceImpl implements ResourceService {
    private Map<String, List<String>> roleResourceMap;

    @Autowired private RedisTemplate redisTemplate;

    @PostConstruct
    public void initData() {
        roleResourceMap = new TreeMap<>();
        roleResourceMap.put("/member/hello/test", CollUtil.toList("ADMIN"));
        roleResourceMap.put("/member/usr/curt", CollUtil.toList("ADMIN", "TEST"));



        redisTemplate.opsForHash().putAll(RedisConstant.RESOURCE_ROLES_MAP, roleResourceMap);
    }


}
