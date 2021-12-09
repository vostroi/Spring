package com.vostroi.service;

import com.vostroi.bean.Order;

import java.util.List;

/**
 * @author Administrator
 * @title: service
 * @projectName sp_dbo
 * @description: TODO
 * @date 2020/5/1315:08
 */
public interface OrderService {

    public List<Order> getByCustomerId(String customerId);


}
