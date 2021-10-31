package com.whiplash.components.order.bean;

import com.whiplash.core.commom.util.EnumConstantOrder;
import com.whiplash.core.commom.util.EnumConstantPayment;
import com.whiplash.core.platform.bean.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 * @date 2021/9/5 16:59
 * @projectName whiplash
 * @title: Order
 * @description: 用户下单的总订单
 * 订单关系：Order -> MerchantOrder -> StoreOrder -> OrderProduct
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "t_order")
public class Order extends BaseEntity {

    /**
     * 订单号
     */
    @Column(name = "order_code" , columnDefinition = "bigint")
    private Long orderCode;

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

    /**
     * 映射到数据库 默认是按 枚举值序列 EnumType.ORDINAL，如果要使用自定义值 需要 @Convert 并 自定义实现  AttributeConverter
     */
    @Column(name = "status" , columnDefinition = "int")
    private EnumConstantOrder.ORDER_STATUS status = EnumConstantOrder.ORDER_STATUS.NOT_PAYED;

    @Column(name = "pay_way" , columnDefinition = "int")
    private EnumConstantPayment.PAY_WAY payWay ;

    @Transient
    private List<MerchantOrder> moList;

    public List<MerchantOrder> getMoList() {
        if (this.moList == null) {
            this.moList = new ArrayList<>();
        }
        return this.moList;
    }
}
