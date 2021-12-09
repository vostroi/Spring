package com.whiplash.member.service;

import com.whiplash.components.member.bean.Customer;
import com.whiplash.core.platform.service.BaseService;
import com.whiplash.dto.CustomerDto;

/**
 * @author Administrator
 * @date 2021/9/5 18:11
 * @projectName whiplash
 * @title: CustomerService
 * @description: TODO
 */
public interface CustomerService extends BaseService<Customer, Long> {

    public Customer saveFrom(CustomerDto customerDto);

}
