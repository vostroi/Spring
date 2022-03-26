package com.whiplash.order.service;

import com.whiplash.components.order.bean.Order;
import com.whiplash.components.order.dto.OrderDto;
import com.whiplash.components.order.dto.SingleOrderSubmitDto;
import com.whiplash.core.commom.util.ResultData;
import com.whiplash.core.platform.service.BaseService;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @date 2021/10/26 14:01
 * @projectName whiplash
 * @title: OrderMobileService
 * @description: 移动端
 */
@Service
public interface OrderMobileService extends BaseService<Order , Long> {

    /**
     * 移动端 直接下单
     * @param sosd
     * @return
     */
    public ResultData<OrderDto> singleSubmitOrder(SingleOrderSubmitDto sosd);


}
