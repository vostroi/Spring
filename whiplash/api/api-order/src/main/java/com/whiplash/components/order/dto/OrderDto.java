package com.whiplash.components.order.dto;

import com.whiplash.core.commom.util.EnumConstantOrder;
import com.whiplash.core.commom.util.EnumConstantPayment;
import com.whiplash.core.platform.bean.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 * @date 2021/10/26 16:25
 * @projectName whiplash
 * @title: OrderDto
 * @description: TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class OrderDto extends BaseDto {

    /**
     * 订单号
     */
    private Long orderCode;

    /**
     * 订单总价 单位元
     */
    private BigDecimal orderPrice = BigDecimal.ZERO;

    /**
     * 支付金额 单位元
     */
    private BigDecimal payAmount = BigDecimal.ZERO;

    /**
     * 优惠金额 元
     */
    private BigDecimal discountAmount = BigDecimal.ZERO;

    /**
     * 到账金额 单位元
     */
    private BigDecimal receivedAmount = BigDecimal.ZERO;

    /**
     * 手续费金额 单位元
     */
    private BigDecimal feeAmount = BigDecimal.ZERO;

    /**
     * 买家 member.id
     */
    private Long buyer;

    /**
     * 支付时间
     */
    private Date payTime;

    /**
     * 商品数量
     */
    private int skuCount;

    private EnumConstantOrder.ORDER_STATUS status = EnumConstantOrder.ORDER_STATUS.NOT_PAYED;

    private EnumConstantPayment.PAY_WAY payWay ;

    private List<MerchantOrderDto> mos;
}
