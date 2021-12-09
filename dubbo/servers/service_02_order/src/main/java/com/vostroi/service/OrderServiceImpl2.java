package com.vostroi.service;


import com.vostroi.bean.Order;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @author Administrator
 * @title: OrderServiceImpl
 * @projectName sp_dbo
 * @description: TODO
 * @date 2020/5/1315:30
 */
@Service
public class OrderServiceImpl2 implements OrderService {
    @Override
    public List<Order> getByCustomerId(String customerId) {
        Order order1 = new Order(UUID.randomUUID().toString(), "中国 四川 成都 ", "张三");
        Order order2 = new Order(UUID.randomUUID().toString(), "中国 四川 眉山 ", "李四");
        Order order3 = new Order(UUID.randomUUID().toString(), "中国 北京 朝阳 ", "王五");

        return Arrays.asList(order1, order2, order3);
    }

}
