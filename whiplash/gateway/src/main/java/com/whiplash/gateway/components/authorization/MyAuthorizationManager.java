package com.whiplash.gateway.components.authorization;

import cn.hutool.core.convert.Convert;
import com.whiplash.core.commom.util.OauthConstant;
import com.whiplash.core.commom.util.RedisConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Administrator
 * @date 2021/9/19 15:42
 * @projectName whiplash
 * @title: MyAuthorizationManager
 * @description: 鉴权管理器，用于判断是否有权限访问资源
 */
@Component
public class MyAuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    @Autowired RedisTemplate<String , Object> redisTemplate;


    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        // 请求的资源路径
        URI uri = authorizationContext.getExchange().getRequest().getURI();

        // 从 redis 获取当前 请求资源 可访问的 角色
        Object objs = redisTemplate.opsForHash().get(RedisConstant.RESOURCE_ROLES_MAP, uri.getPath());

        List<String> authorities = Convert.toList(String.class, objs);

        authorities = authorities.stream().map(a ->
            a = OauthConstant.AUTHORITY_PREFIX + a
        ).collect(Collectors.toList());

        Mono<AuthorizationDecision> authorizationDecisionMono = mono.filter(Authentication::isAuthenticated)
                .flatMapIterable(Authentication::getAuthorities)
                .map(GrantedAuthority::getAuthority)
                .any(authorities::contains)
                .map(AuthorizationDecision::new)
                .defaultIfEmpty(new AuthorizationDecision(false));

        return authorizationDecisionMono;
    }


}
