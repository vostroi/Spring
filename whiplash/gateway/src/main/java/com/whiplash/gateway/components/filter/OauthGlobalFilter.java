package com.whiplash.gateway.components.filter;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.nimbusds.jose.JWSObject;
import com.whiplash.core.commom.util.OauthConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.text.ParseException;

/**
 * @author Administrator
 * @date 2021/9/19 22:09
 * @projectName whiplash
 * @title: OauthGlobalFilter
 * @description: 全局过滤器，当鉴权通过后，将 JWT 令牌中的用户信息解析出来，存入请求的 header 中，这样后续服务无需再解析 JWT ， 可以直接从 header 中获取用户信息
 */
@Slf4j
@Component
public class OauthGlobalFilter implements GlobalFilter , Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst(OauthConstant.AUTHORIZATION);
        if (StrUtil.isEmpty(token)) {
            return chain.filter(exchange);
        }

        try {
            String pureToken = token.replace(OauthConstant.BEARER, "");
            JWSObject jwsObject = JWSObject.parse(pureToken);
            String userInfoStr = jwsObject.getPayload().toString();
            log.debug("OauthGlobalFilter.filter() userInfoStr:{}", userInfoStr);
            ServerHttpRequest request = exchange.getRequest().mutate().header("user", userInfoStr).build();
            exchange = exchange.mutate().request(request).build();

        } catch (ParseException e) {
            log.error("解析token中用户信息异常：", e);
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
