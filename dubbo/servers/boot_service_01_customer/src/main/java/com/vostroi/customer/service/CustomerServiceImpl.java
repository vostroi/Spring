package com.vostroi.customer.service;

import com.vostroi.bean.Order;
import com.vostroi.service.OrderService;
import com.vostroi.service.service.CustomerService;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;

import java.util.List;

/**
 * @title: CustomerServiceImpl
 * @projectName sp_dbo
 * @description: TODO
 * @author Administrator
 * @date 2020/5/1417:33
 */
@Service(interfaceClass = com.vostroi.service.service.CustomerService.class)
public class CustomerServiceImpl implements CustomerService {
    /**
     * version 指定服务提供方 版本号
     * group 服务提供方分组
     * stub 本地存根 用户调用服务之前的逻辑处理
     * mock 本地伪装 适用于经常要捕获RPCException的情况
     * loadbalance="leastactive" 会调用并发数（Consumer端并发连接数）最小的的Provider
     */
    @Reference(version = "1.0.0" ,loadbalance = "leastactive")
    OrderService orderService;


    @Override
    public List<Order> getOrderList(String customerId) {

        return orderService.getByCustomerId(customerId);
    }
}
