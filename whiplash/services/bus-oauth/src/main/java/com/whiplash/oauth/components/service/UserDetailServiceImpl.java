package com.whiplash.oauth.components.service;

import com.whiplash.components.oauth.bean.WhiplashUsers;
import com.whiplash.core.commom.util.OauthConstant;
import com.whiplash.oauth.dto.SecurityUser;
import com.whiplash.oauth.service.WhiplashUsersService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.security.auth.login.AccountExpiredException;
import javax.security.auth.login.CredentialExpiredException;

/**
 * @author Administrator
 * @date 2021/9/12 19:08
 * @projectName whiplash
 * @title: UserDetailServiceImpl
 * @description: 实现 SpringSecurity 的 UserDetailsService ，用于登录， 加载用户信息（调用本系统接口）
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired private WhiplashUsersService wuService;


    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        WhiplashUsers wu = wuService.getByUserName(userName);
        if (wu == null) {
            throw  new UsernameNotFoundException(OauthConstant.USER_NOT_EXIST);
        }

        SecurityUser su = new SecurityUser(wu);
        if (!su.getEnabled()) {
            throw new DisabledException(OauthConstant.USER_DISABLED);
        }
        if (!su.isAccountNonLocked()) {
            throw new LockedException(OauthConstant.USER_LOCKED);
        }
        if (!su.isAccountNonExpired()) {
            throw new AccountExpiredException(OauthConstant.ACCESS_TOKEN_EXPIRED);
        }
        if (!su.isCredentialsNonExpired()) {
            throw new CredentialExpiredException(OauthConstant.ACCESS_TOKEN_EXPIRED);
        }

        return su;

    }




    /**
     * @PostConstruct JDK提供 修饰非静态 void 方法，只会在启动加载 servlet时 运行，且只会执行一次；
     * 该方法在构造方法之后， init方法之前 执行
     * 执行顺序：Constructor(构造方法) -> @Autowired(依赖注入) -> @PostConstruct(注释的方法)
     */
    @PostConstruct
    public void initData() {

    }
}
