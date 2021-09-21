package com.whiplash.oauth.dto;

import com.google.common.collect.Lists;
import com.whiplash.components.oauth.bean.WhiplashUsers;
import com.whiplash.core.commom.util.EnumConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author Administrator
 * @date 2021/9/12 18:54
 * @projectName whiplash
 * @title: User
 * @description: DTO 封装向外传输数据的对象
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SecurityUser implements UserDetails {
    private Long id;

    private String userNmae;

    private String password;

    /**
     * 状态
     */
    private EnumConstant.RECORD_STATE state;

    /**
     * 是否有效
     */
    private Boolean enabled;


    /**
     * 权限
     */
    private Collection<SimpleGrantedAuthority> authorities;

    /**
     * 菜单
     */
    //private List<Menu> menus;

    public SecurityUser(WhiplashUsers wu) {
        if (wu != null) {
            this.setId(wu.getId());
            this.setUserNmae(wu.getUserName());
            // 上线不应该设值
            this.setPassword(wu.getPassword());

            if (EnumConstant.RECORD_STATE.VALID == wu.getState()) {
                this.setEnabled(true);
            }

            // 设置权限
            if (wu.getRoles() != null && !wu.getRoles().isEmpty()) {
                authorities = Lists.newArrayList();
                wu.getRoles().forEach(item -> authorities.add(new SimpleGrantedAuthority(item)));
            }

            // 设置菜单

        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userNmae;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.enabled;
    }

    /**
     * 令牌是否过期？
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return this.enabled;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
