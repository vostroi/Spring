package com.whiplash.order.components.dao;

import com.whiplash.components.order.bean.MerchantOrder;
import com.whiplash.core.platform.dao.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 * @date 2021/12/1 14:35
 * @projectName whiplash
 * @title: MerchantOrderDao
 * @description: TODO
 */
@Repository
public interface MerchantOrderDao extends BaseDao<MerchantOrder , Long> {
}
