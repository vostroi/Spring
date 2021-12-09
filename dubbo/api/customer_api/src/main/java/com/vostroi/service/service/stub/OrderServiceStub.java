package com.vostroi.service.service.stub;

import com.vostroi.bean.Order;
import com.vostroi.service.OrderService;

import java.util.List;

/**
 * @author Administrator
 * @title: OrderStubService
 * @projectName sp_dbo
 * @description: OrderService 服务的本地存根方法
 * @date 2020/5/1916:15
 */
public class OrderServiceStub implements OrderService {

    /**
     * 远程代理对象
     */
    private final OrderService orderService;

    /**
     * 构造函数传入真正的远程代理对象
     * @param orderService
     */
    public OrderServiceStub(OrderService orderService){
        this.orderService = orderService;
    }

    @Override
    public List<Order> getByCustomerId(String customerId) {
        // 这里在本地处理一些逻辑 参数参评 ThreadLocal缓存等
        // 失败 返回组装数据
        System.out.println("OrderServiceStub...");
        return orderService.getByCustomerId(customerId);
    }
}
