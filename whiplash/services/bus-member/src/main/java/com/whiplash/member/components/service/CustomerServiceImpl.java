package com.whiplash.member.components.service;

import com.whiplash.components.member.bean.Customer;
import com.whiplash.core.platform.dao.BaseDao;
import com.whiplash.dto.CustomerDto;
import com.whiplash.member.components.dao.CustomerDao;
import com.whiplash.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @date 2021/9/5 18:18
 * @projectName whiplash
 * @title: CustomerServiceImpl
 * @description: TODO
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired private CustomerDao custDao;

    @Override
    public BaseDao getDao() {
        return custDao;
    }

    @Override
    public Customer getUseCache(Long aLong) {
        return null;
    }

    @Override
    public Customer saveFrom(CustomerDto customerDto) {
        return null;
    }
}
