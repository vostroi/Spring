package com.vostroi.customer.controller;

import com.vostroi.service.service.CustomerService;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @title: CustomerController
 * @projectName sp_dbo
 * @description: TODO
 * @date 2020/5/1417:44
 */
@RestController
@RequestMapping(value="cust")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/orders/{customerId}")
    public Object getOrderList( @PathVariable String customerId){
        return customerService.getOrderList(customerId);
    }

    /**
     * dubbo admin 报没有元数据错误
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

            CuratorFramework zkClient = CuratorFrameworkFactory.builder().
                    connectString("127.0.0.1:2181").
                    retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
            zkClient.start();

            if (zkClient.checkExists().forPath("/dubbo/config/dubbo/dubbo.properties") == null) {
                zkClient.create().creatingParentsIfNeeded().forPath("/dubbo/config/dubbo/dubbo.properties");
            }
            zkClient.setData().forPath("/dubbo/config/dubbo/dubbo.properties", ("dubbo.registry.address=zookeeper://127.0.0.1:2181\n" +
                    "dubbo.metadata-report.address=zookeeper://127.0.0.1:2181").getBytes());
    }


}
