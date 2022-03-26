package com.whiplash.components.tool;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.whiplash.core.commom.util.OauthConstant;
import com.whiplash.core.platform.bean.BaseDto;
import com.whiplash.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.security.auth.login.CredentialExpiredException;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Administrator
 * @date 2021/9/19 22:37
 * @projectName whiplash
 * @title: LoginUserHolder
 * @description: 公共组件，用于从请求的header中 获取用户信息
 */
@Component
@Slf4j
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

    /**
     * 校验 发起请求人 是否 匹配 当前 JWT
     * @param custId
     * @return true 不匹配 非法请求
     */
    public boolean illegalRequest(String custId) throws CredentialExpiredException {
        log.debug("校验jwt是否匹配 custId={}",custId);
        if (StrUtil.isEmpty(custId)) {
            return true;
        }

        UserDto usrDto = this.getCurrentUser();
        if (usrDto == null) {
            throw new CredentialExpiredException(OauthConstant.ACCESS_TOKEN_EXPIRED);
        }

        return false;
    }

    /**
     * 校验 发起请求人 是否 匹配 当前 JWT
     * @param baseDto
     * @return true 不匹配 非法请求
     */
//    public boolean illegalRequest(BaseDto baseDto) {
//        if (baseDto == null) {
//            return true;
//        }
//
//        if(StrUtil.isEmpty(baseDto.get))
//
//    }


}
