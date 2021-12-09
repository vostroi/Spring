package com.vostroi.service.service;

import com.vostroi.bean.Order;

import java.util.List;

/**
 * @author Administrator
 * @title: CustomerService
 * @projectName sp_dbo
 * @description: TODO
 * @date 2020/5/1314:58
 */
public interface CustomerService {

    public List<Order> getOrderList(String customerId);

}
