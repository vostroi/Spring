package com.whiplash.dto;

import com.whiplash.core.commom.util.CommomConstant;
import com.whiplash.core.commom.util.EnumConstantProduct;
import com.whiplash.core.platform.bean.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author Administrator
 * @date 2021/10/28 17:22
 * @projectName whiplash
 * @title: ProductSpecsDto
 * @description: TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ProductSpecsDto extends BaseDto {

    /**
     * 商品编号
     */
    private Long productId;

    /**
     * 规格名称
     */
    private String specsName = CommomConstant.STR_EMPTY;

    /**
     * 规格状态
     */
    private EnumConstantProduct.PRODUCT_STATUS status = EnumConstantProduct.PRODUCT_STATUS.DRAFT;

    private String statusName = status.getName();

    private BigDecimal price=BigDecimal.ZERO;

    /**
     * 规格可用库存
     */
    private BigInteger stockLeft = BigInteger.ZERO;

    /**
     * 规格冻结库存
     */
    private BigInteger stockFrozen = BigInteger.ZERO;

    /**
     * 销量
     */
    private BigInteger sales = BigInteger.ZERO;

}
