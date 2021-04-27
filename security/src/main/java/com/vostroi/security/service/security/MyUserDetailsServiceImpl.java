package com.vostroi.security.service.security;

import com.vostroi.security.comm.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @date 2021/4/24 22:37
 * @projectName security
 * @title: MyUserDetailsServiceImpl
 * @description: SpringSecurity 默认的登录逻辑是  UserDetailsService
 * 自定义登录逻辑，实现SpringSecurity的UserDetailsService接口 覆盖 loadUserByUsername
 */
@Service
@Slf4j
public class MyUserDetailsServiceImpl implements UserDetailsService {
    @Autowired private PasswordEncoder passwordEncoder;
    /**
     * 自定义登录逻辑
     * 从数据库中获取用户信息
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 以username去数据库中查询用户，不存在直接抛UsernameNotFoundException异常
        if (!Constants.USER_NAME_ADMIN.equals(username)) {
            throw new UsernameNotFoundException("用户不存在");
        }

        // 用户存在，比较密码（密码比较过程 security内部会调用验证）

        // 封装用户信息, 密码必须是加密后的密码（存在在数据库中的密码）
        User user = new User(username, Constants.USER_NAME_ADMIN_PASSWORD_ENCODED , AuthorityUtils.commaSeparatedStringToAuthorityList("ADMIN,MANAGER"));
        log.info("loadUserByUsername={}", user);
        return user;
    }
}
