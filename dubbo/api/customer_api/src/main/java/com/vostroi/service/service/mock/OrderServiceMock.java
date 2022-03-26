package com.vostroi.service.service.mock;

import com.vostroi.bean.Order;
import com.vostroi.service.OrderService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @title: OrderServiceMock
 * @projectName sp_dbo
 * @description: 1、本地伪装 通常用于服务降级，当服务提供方全部挂掉后，客户端不抛出异常，而是返回失败数据
 *              2、 默认出现RPC异常， 才会触发 本地伪装
 * @date 2020/5/1916:49
 */
public class OrderServiceMock implements OrderService {
    public OrderServiceMock(){

    }


    @Override
    public List<Order> getByCustomerId(String customerId) {
        System.out.println("OrderServiceMock...");
        return new ArrayList<>();
    }
}
