package com.vostroi.order.service;

import ch.qos.logback.core.util.TimeUtil;
import com.vostroi.bean.Order;
import com.vostroi.service.OrderService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @title: OrderServiceImpl
 * @projectName sp_dbo
 * @description: TODO
 * @date 2020/5/1417:41
 */
@Service(version = "1.0.0" , executes = 5 , actives = 5 )
public class OrderServiceImpl implements OrderService {
    @Override
    public List<Order> getByCustomerId(String customerId) {
        Order order1 = new Order(UUID.randomUUID().toString(),"中国 四川 成都 " , "张三");
        System.out.println(1);
        try {
            TimeUnit.SECONDS.sleep(8);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        Order order2 = new Order(UUID.randomUUID().toString(),"中国 四川 眉山 " , "李四");
        System.out.println(2);
        Order order3 = new Order(UUID.randomUUID().toString(),"中国 北京 朝阳 " , "王五");
        System.out.println(3);
//        int i = 1 / 0 ;
        return Arrays.asList(order1 , order2 , order3);
    }
}
