package com.whiplash.components.order.dto;

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
public class OrderProductDto extends BaseDto {

    private Long orderId;

    private Long merchantOrderId;

    private Long StoreOrderId;

    private Long merchantId;

    private Long storeId;

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
     * createTime 为下单时间
     * 支付时间
     */
    private Date payTime;

    private Long productId;

    private Long productSpecsId;

    private String skuName;

    private String productSpecsName;

    /**
     * 商品数量
     */
    private int skuCount;
}
