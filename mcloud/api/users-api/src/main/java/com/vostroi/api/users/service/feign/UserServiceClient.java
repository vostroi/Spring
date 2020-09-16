package com.vostroi.api.users.service.feign;

import com.vostroi.util.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Administrator
 * @date 2020/9/14 16:32
 * @projectName mcloud
 * @title: UserServiceClient
 * @description: 提供给服务 消费者 负载均衡 调用 ; 其中的方法 与 controller 的方法一一对应
 * 看名字与 UserService 有点相似， 但不能使用 UserService 作为 feign 客户端
 *  1.UserService 添加了接口 其实现类必须得有实现
 *  2.UserService 若使用现成的 接口， 匹配度 可能不是那么OK
 *  3.Bean 扫描的时候 会有名称重复，造成麻烦
 *
 *  fallbackFactory 处理服务降级
 */
@FeignClient(value = "users" ,  fallbackFactory = UserServiceClientFallBack.class)
public interface UserServiceClient {

    @GetMapping(value = "/usr/get/{id}")
    ResultData getUserById(@PathVariable("id") String id);

    @GetMapping(value = "/usr/calc")
    ResultData calculatePrice();
}
