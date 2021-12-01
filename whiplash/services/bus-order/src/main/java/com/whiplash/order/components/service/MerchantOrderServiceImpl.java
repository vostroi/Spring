package com.whiplash.order.components.service;

import com.whiplash.components.order.bean.MerchantOrder;
import com.whiplash.core.platform.dao.BaseDao;
import com.whiplash.order.components.dao.MerchantOrderDao;
import com.whiplash.service.MerchantOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @date 2021/12/1 14:28
 * @projectName whiplash
 * @title: MerchantOrderServiceImpl
 * @description: TODO
 */
@Service
public class MerchantOrderServiceImpl implements MerchantOrderService {
    @Autowired private MerchantOrderDao moDao;
    @Override
    public BaseDao<MerchantOrder, Long> getDao() {
        return moDao;
    }

    @Override
    public MerchantOrder getUseCache(Long aLong) {
        return null;
    }



}
