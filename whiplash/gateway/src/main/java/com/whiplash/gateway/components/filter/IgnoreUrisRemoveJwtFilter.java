package com.whiplash.gateway.components.filter;

import com.whiplash.core.commom.util.OauthConstant;
import com.whiplash.gateway.components.config.IgnoreUriConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

/**
 * @author Administrator
 * @date 2021/9/19 16:47
 * @projectName whiplash
 * @title: IgnoreUrisRemoveJwtFilter
 * @description: 需要移除 白名单 路径访问时的 jwt 头部请求
 */
@Component
public class IgnoreUrisRemoveJwtFilter implements WebFilter {
    @Autowired private IgnoreUriConfig ignoreUriConfig;

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {
        ServerHttpRequest request = serverWebExchange.getRequest();
        URI uri = request.getURI();

        PathMatcher pathMatcher = new AntPathMatcher();

        List<String> uris = ignoreUriConfig.getUris();
        if(uris!=null && !uris.isEmpty()){
            for (String u : uris) {
                if (pathMatcher.match(u, uri.getPath())) {
                    request = serverWebExchange.getRequest().mutate().header(OauthConstant.AUTHORIZATION, "").build();
                    serverWebExchange = serverWebExchange.mutate().request(request).build();
                    return webFilterChain.filter(serverWebExchange);
                }
            }
        }

        return webFilterChain.filter(serverWebExchange);
    }

}
