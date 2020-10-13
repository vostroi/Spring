package com.vostroi.zuulgateway.config;

import com.google.common.collect.ImmutableMap;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

/**
 * @author Administrator
 * @date 2020/10/10 15:41
 * @projectName mcloud
 * @title: ZuulConfig
 * @description: 处理 服务 熔断 降级
 */
@Component
@Slf4j
public class ZuulConfig implements FallbackProvider {
    /**
     * 返回值 为要监控的服务
     * 如果需要所有调用都支持回退 return null 或者  return "*"
     * @return
     */
    @Override
    public String getRoute() {
        return "*";
    }

    /**
     * 熔断时 作出的处理
     * @param route
     * @param cause
     * @return
     */
    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        log.info("--> zuul route:{} fallback 熔断处理 ", route);
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return getStatusCode().value();
            }

            @Override
            public String getStatusText() throws IOException {
                return getStatusCode().getReasonPhrase();
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                String result = new GsonBuilder().create().toJson(ImmutableMap.of("errorCode", 500, "content", "服务暂时忙，请稍候...", "time", LocalDateTime.now()));
                return new ByteArrayInputStream(result.getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                MediaType mt = new MediaType("application", "json", StandardCharsets.UTF_8);
                headers.setContentType(mt);
                return headers;
            }
        };
    }
}
