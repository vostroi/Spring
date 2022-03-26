package com.vostroi.security.service.security;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

/**
 * @author Administrator
 * @date 2021/5/2 10:48
 * @projectName security
 * @title: MyAccessServiceImpl
 * @description: TODO
 */
@Service
@Slf4j
public class MyAccessServiceImpl implements MyAccessService {
    // 注入权限相关service用于匹配
    //

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        log.info("进入 MyAccessServiceImpl hasPermission {}", request.getRequestURI());
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails usrDtl = (UserDetails) principal;
            // 判断urdDtl拥有的权限
            Collection<? extends GrantedAuthority> authorities = usrDtl.getAuthorities();
            String requestURI = request.getRequestURI();
            return authorities.contains(new SimpleGrantedAuthority(requestURI));
        }

        log.error("主体不正确");
        return false;
    }
}
