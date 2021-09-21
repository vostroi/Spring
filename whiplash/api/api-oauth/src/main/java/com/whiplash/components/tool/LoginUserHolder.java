package com.whiplash.components.tool;

import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONObject;
import com.whiplash.core.commom.util.OauthConstant;
import com.whiplash.dto.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Administrator
 * @date 2021/9/19 22:37
 * @projectName whiplash
 * @title: LoginUserHolder
 * @description: 公共组件，用于从请求的header中 获取用户信息
 */
@Component
public class LoginUserHolder {

    public UserDto getCurrentUser() {
        // 从 header 中获取用户信息
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = servletRequestAttributes.getRequest();

        String userInfoStr = request.getHeader(OauthConstant.USER);

        JSONObject jsonObject = new JSONObject(userInfoStr);
        UserDto userDto = jsonObject.toBean(UserDto.class);
        userDto.setUserName(jsonObject.getStr("user_name"));
        userDto.setRoles(Convert.toList(String.class, jsonObject.get("authorities")));
        return userDto;
    }

}
