package com.vostroi.securityoauth2.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * @author Administrator
 * @date 2021/5/2 10:13
 * @projectName security
 * @title: AccessForbiddenHandler
 * @description: 自定义请求无权限，拒绝请求处理
 */
@Component
public class AccessForbiddenHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        httpServletResponse.setHeader("Content-type", "application/json;charset=utf-8");
        PrintWriter writer = httpServletResponse.getWriter();
        HashMap<Object, Object> result = new HashMap<>();
        result.put("STATUS" , HttpServletResponse.SC_FORBIDDEN);
        result.put("MESSAGE" , "无访问权限");
        writer.write( new ObjectMapper().writeValueAsString(result));
        writer.flush();
        writer.close();
    }
}
