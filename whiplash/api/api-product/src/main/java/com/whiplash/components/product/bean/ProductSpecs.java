package com.whiplash.components.product.bean;

import com.whiplash.core.commom.util.CommomConstant;
import com.whiplash.core.commom.util.EnumConstantProduct;
import com.whiplash.core.platform.bean.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author Administrator
 * @date 2021/10/25 17:40
 * @projectName whiplash
 * @title: Product
 * @description: 商品-规格
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "t_product_specs")
public class ProductSpecs extends BaseEntity {

    /**
     * 商品编号
     */
    @Column(name = "product_id" , columnDefinition = "bigint")
    private Long productId;

    /**
     * 规格名称
     */
    @Column(name = "specs_name" , columnDefinition = "varchar(64)")
    private String specsName = CommomConstant.STR_EMPTY;

    /**
     * 规格状态
     */
    @Column(name = "status" , columnDefinition = "int")
    private EnumConstantProduct.PRODUCT_STATUS status = EnumConstantProduct.PRODUCT_STATUS.DRAFT;

    @Column(name="price" , columnDefinition = "decimal(10,2) NULL DEFAULT 0")
    private BigDecimal price=BigDecimal.ZERO;

    /**
     * 规格可用库存
     */
    @Column(name = "stock_left" , columnDefinition = "bigint")
    private BigInteger stockLeft = BigInteger.ZERO;

    /**
     * 规格冻结库存
     */
    @Column(name = "stock_frozer" , columnDefinition = "bigint")
    private BigInteger stockFrozen = BigInteger.ZERO;

    /**
     * 销量
     */
    @Column(name = "sales" , columnDefinition = "bigint")
    private BigInteger sales = BigInteger.ZERO;

}
