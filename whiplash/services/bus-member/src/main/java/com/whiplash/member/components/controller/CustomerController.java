package com.whiplash.member.components.controller;

import com.whiplash.components.member.bean.Customer;
import com.whiplash.core.platform.controller.BaseController;
import com.whiplash.core.platform.service.BaseService;
import com.whiplash.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
