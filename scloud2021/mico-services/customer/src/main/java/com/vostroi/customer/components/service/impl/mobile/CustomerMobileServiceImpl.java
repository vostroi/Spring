package com.vostroi.customer.components.service.impl.mobile;

import com.vostroi.api.components.beans.Customer;
import com.vostroi.api.service.mobile.CustomerMobileService;
import com.vostroi.components.dao.BaseDao;
import com.vostroi.customer.components.dao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public BaseDao<Customer, Long> getDao() {
        return dao;
    }

}
