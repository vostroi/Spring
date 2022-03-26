package com.whiplash.member.components.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.whiplash.components.member.bean.Customer;
import com.whiplash.core.commom.util.ResultData;
import com.whiplash.core.platform.controller.BaseController;
import com.whiplash.core.platform.service.BaseService;
import com.whiplash.dto.CustomerDto;
import com.whiplash.member.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Administrator
 * @date 2021/9/5 18:17
 * @projectName whiplash
 * @title: CustomerController
 * @description: 系统会员API
 */
@Slf4j
@RestController
@RequestMapping(value = "/cust")
public class CustomerController extends BaseController<Customer, Long> {

    @Autowired  private CustomerService custService;

    @Override
    public BaseService<Customer, Long> getService() {
        return custService;
    }

    /**
     * 新用户注册并登录
     * 手机号+验证码
     *
     * @return
     */
    @PostMapping(value = "/su/sms")
    public ResultData<CustomerDto> signUp(String json) {
        if(StrUtil.isEmpty(json)){
            return ResultData.getResultDataParamEmpty();
        }

        CustomerDto cd = JSONUtil.toBean(json, CustomerDto.class);

        Customer customer = custService.saveFrom(cd);

        CustomerDto cd2 = CustomerDto.fromCustomerBrief(customer);

        return ResultData.getResultDataSuccess(cd2);
    }

    /**
     * 获取用户简要信息
     * @param id
     * @return
     */
    @GetMapping(value = "/get/brief/{id}")
    public ResultData<CustomerDto> getCustomerBrief(@PathVariable("id") Long id){
        Customer customer = custService.get(id);
        return ResultData.getResultDataSuccess(CustomerDto.fromCustomerBrief(customer));
    }

    /**
     * 获取用户的所有信息
     * @param id
     * @return
     */
    @GetMapping(value = "/get/full/{id}")
    public ResultData<CustomerDto> getCustomerFull(@PathVariable("id") Long id){
        Customer customer = custService.get(id);
        CustomerDto custDto = CustomerDto.fromBaseEntityFull(customer , CustomerDto.class);
        return ResultData.getResultDataSuccess(custDto);
    }



}
