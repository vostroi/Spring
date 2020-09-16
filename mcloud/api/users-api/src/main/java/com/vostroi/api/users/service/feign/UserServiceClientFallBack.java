package com.vostroi.api.users.service.feign;

import com.vostroi.util.EnumConstant;
import com.vostroi.util.ResultData;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Administrator
 * @date 2020/9/14 16:32
 * @projectName mcloud
 * @title: UserServiceClient
 * @description: 处理服务降级 要 配合  UserServiceClient 一起使用
 */
@Component
public class UserServiceClientFallBack implements FallbackFactory<UserServiceClient> {
    @Override
    public UserServiceClient create(Throwable throwable) {
        return new UserServiceClient(){
            @Override
            public ResultData getUserById(String id) {
                return ResultData.getResultData(EnumConstant.RESULT_CODE.ER_3333_2222 , "服务器繁忙");
            }

            @Override
            public ResultData calculatePrice() {
                return ResultData.getResultData(EnumConstant.RESULT_CODE.ER_3333_2222 , "服务器繁忙");
            }
        };
    }
}
