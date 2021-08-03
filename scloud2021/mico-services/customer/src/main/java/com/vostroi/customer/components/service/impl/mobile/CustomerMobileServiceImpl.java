package com.vostroi.customer.components.service.impl.mobile;

import com.vostroi.api.components.beans.Customer;
import com.vostroi.api.feign.product.mobile.ProductMobileClient;
import com.vostroi.api.service.mobile.CustomerMobileService;
import com.vostroi.components.dao.BaseDao;
import com.vostroi.customer.components.dao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author Administrator
 * @date 2021/5/15 18:11
 * @projectName scloud2021
 * @title: CustomerMobileServiceImpl
 * @description: TODO
 */
@Service
public class CustomerMobileServiceImpl implements CustomerMobileService {
    @Autowired private CustomerDao dao;

    @Autowired private ProductMobileClient productClient;

    @Override
    public BaseDao<Customer, Long> getDao() {
        return dao;
    }

    @Override
    public String testSeata(Long custId) {

        Customer cust = get(custId);
        if(cust==null){
            return "FAIL";
        }

        cust.setName(UUID.randomUUID().toString());

        save(cust);

        productClient.setPrice(234L, new BigDecimal("11.22"));

        return "SUCCESS";
    }
}
