package com.whiplash.member.components.dao;

import com.whiplash.components.member.bean.Customer;
import com.whiplash.core.platform.dao.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 * @date 2021/9/5 18:15
 * @projectName whiplash
 * @title: CustomerDao
 * @description: TODO
 */
@Repository
public interface CustomerDao extends BaseDao<Customer, Long> {



}
