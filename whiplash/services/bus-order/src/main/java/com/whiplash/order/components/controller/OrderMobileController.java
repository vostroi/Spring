package com.whiplash.order.components.controller;

import com.whiplash.components.order.bean.Order;
import com.whiplash.components.order.dto.OrderDto;
import com.whiplash.components.order.dto.SingleOrderSubmitDto;
import com.whiplash.components.tool.LoginUserHolder;
import com.whiplash.core.commom.util.ResultData;
import com.whiplash.core.platform.controller.BaseController;
import com.whiplash.core.platform.service.BaseService;
import com.whiplash.dto.UserDto;
import com.whiplash.order.service.OrderMobileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Administrator
 * @date 2021/9/5 18:17
 * @projectName whiplash
 * @title: OrderMobileController
 * @description: 订单 API 移动端
 */
@Slf4j
@RestController
@RequestMapping(value = "/ord/mbl")
public class OrderMobileController extends BaseController<Order, Long> {

    @Autowired private OrderMobileService ordMblService;
    @Autowired private LoginUserHolder luHolder;

    @Override
    public BaseService<Order, Long> getService() {
        return ordMblService;
    }

    /**
     * 单商品 直接下单
     * @return
     */
    @PostMapping(value = "/sigl/sub")
    public ResultData<OrderDto> singleSubmitOrder(@RequestBody SingleOrderSubmitDto sosd , HttpServletRequest request) {
        // 以此来检查 参数的custId 和 token 中的 custId 是否匹配
        UserDto ud = luHolder.getCurrentUser();

        // luHolder.illegalRequest(sosd.getCustId()+"");

        ResultData<OrderDto> rd = ordMblService.singleSubmitOrder(sosd);

        return rd;
    }



}
