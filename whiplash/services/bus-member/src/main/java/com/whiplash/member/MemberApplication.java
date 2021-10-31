package com.whiplash.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Administrator
 * @date 2021/9/2 16:07
 * @projectName whiplash
 * @title: MemberApplication
 * @description: TODO
 */
@SpringBootApplication
@EnableDiscoveryClient
@EntityScan(basePackages = {"com.whiplash.components.member.bean"})
@ComponentScan(basePackages = {"com.whiplash.components.tool","cn.hutool.extra.spring","com.whiplash.member.components"})
public class MemberApplication {
    public static void main(String[] args) {
        SpringApplication.run(MemberApplication.class, args);
    }
}
