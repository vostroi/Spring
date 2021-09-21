package com.whiplash.components.order.bean;

import com.whiplash.core.platform.bean.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Administrator
 * @date 2021/9/5 17:49
 * @projectName whiplash
 * @title: MerchantOrder
 * @description: 商户订单
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "t_merchant_order")
public class MerchantOrder extends BaseEntity {


    @Column(name = "order_id" , columnDefinition = "bigint")
    private Long orderId;

    @Column(name = "merchant_id" , columnDefinition = "bigint")
    private Long merchantId;

    /**
     * 订单总价 单位元
     */
    @Column(name = "order_price" , columnDefinition = "decimal(10,2) NULL DEFAULT 0")
    private BigDecimal orderPrice = BigDecimal.ZERO;

    /**
     * 支付金额 单位元
     */
    @Column(name = "pay_amount" , columnDefinition = "decimal(10,2) NULL DEFAULT 0")
    private BigDecimal payAmount = BigDecimal.ZERO;

    /**
     * 优惠金额 元
     */
    @Column(name = "discount_amount" , columnDefinition = "decimal(10,2) NULL DEFAULT 0")
    private BigDecimal discountAmount = BigDecimal.ZERO;

    /**
     * 到账金额 单位元
     */
    @Column(name = "received_amount" , columnDefinition = "decimal(10,2) NULL DEFAULT 0")
    private BigDecimal receivedAmount = BigDecimal.ZERO;

    /**
     * 手续费金额 单位元
     */
    @Column(name = "fee_amount" , columnDefinition = "decimal(10,2) NULL DEFAULT 0")
    private BigDecimal feeAmount = BigDecimal.ZERO;

    /**
     * 买家 member.id
     */
    @Column(name = "buyer" , columnDefinition = "bigint")
    private Long buyer;

    /**
     * createTime 为下单时间
     * 支付时间
     */
    @Column(name = "pay_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date payTime;

    /**
     * 商品数量
     */
    @Column(name = "sku_count" , columnDefinition = "int")
    private int skuCount;


}
