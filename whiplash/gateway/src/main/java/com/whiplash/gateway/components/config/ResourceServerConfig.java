package com.whiplash.gateway.components.config;

import cn.hutool.core.util.ArrayUtil;
import com.whiplash.core.commom.util.OauthConstant;
import com.whiplash.gateway.components.authorization.MyAuthorizationManager;
import com.whiplash.gateway.components.filter.IgnoreUrisRemoveJwtFilter;
import com.whiplash.gateway.components.handler.RestAuthenticationEntryPoint;
import com.whiplash.gateway.components.handler.RestfulAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

/**
 * @author Administrator
 * @date 2021/9/19 15:40
 * @projectName whiplash
 * @title: ResourceServerConfig
 * @description: 资源服务器配置
 */
@Configuration
@EnableWebFluxSecurity
public class ResourceServerConfig {
    @Autowired private MyAuthorizationManager authorizationManager;
    @Autowired private IgnoreUriConfig ignoreUriConfig;
    @Autowired private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    @Autowired private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    @Autowired private IgnoreUrisRemoveJwtFilter ignoreUrisRemoveJwtFilter;


    @Bean
    public Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter converter = new JwtGrantedAuthoritiesConverter();
        converter.setAuthorityPrefix(OauthConstant.AUTHORITY_PREFIX);
        converter.setAuthoritiesClaimName(OauthConstant.AUTHORITY_CLAIM_NAME);
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(converter);
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
        httpSecurity.oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthenticationConverter());

        // 自定义处理 JWT 请求头 过期  或  签名错误 结果
        httpSecurity.oauth2ResourceServer().authenticationEntryPoint(restAuthenticationEntryPoint);
        // 处理白名单资源路径，移除 JWT 请求头
        httpSecurity.addFilterBefore(ignoreUrisRemoveJwtFilter, SecurityWebFiltersOrder.AUTHENTICATION);

        httpSecurity.authorizeExchange()
                // 白名单  放行
                .pathMatchers(ArrayUtil.toArray(ignoreUriConfig.getUris(), String.class)).permitAll()
                // 配置 鉴权管理器
                .anyExchange().access(authorizationManager)
                .and().exceptionHandling()
                // 处理未授权请求
                .accessDeniedHandler(restfulAccessDeniedHandler)
                // 处理未认证请求
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and().csrf().disable()
        ;
        return httpSecurity.build();
    }

}
