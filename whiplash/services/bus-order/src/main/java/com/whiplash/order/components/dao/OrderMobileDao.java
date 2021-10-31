package com.whiplash.order.components.dao;

import com.whiplash.components.member.bean.Customer;
import com.whiplash.components.order.bean.Order;
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
public interface OrderMobileDao extends BaseDao<Order, Long> {



}
