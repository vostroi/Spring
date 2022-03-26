package com.whiplash.gateway.components.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Administrator
 * @date 2021/9/19 16:12
 * @projectName whiplash
 * @title: IgnoreUriConfig
 * @description: 网关 白名单配置
 */
@Data
@EqualsAndHashCode(callSuper = false)

@Component
@ConfigurationProperties(prefix = "security.ignore")
public class IgnoreUriConfig {
    private List<String> uris;

}
