package com.vostroi.service;

import com.vostroi.bean.Order;
import com.vostroi.service.OrderService;
import com.vostroi.service.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 * @title: CustomerServiceImpl
 * @projectName sp_dbo
 * @description: TODO
 * @date 2020/5/1315:21
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private OrderService orderService;



    @Override
    public List<Order> getOrderList(String customerId) {
        return orderService.getByCustomerId(customerId);
    }
}
