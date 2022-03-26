package com.vostroi.api.components.beans;

import com.vostroi.components.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Administrator
 * @date 2021/5/15 16:40
 * @projectName scloud2021
 * @title: Product
 * @description: TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity {

    /**
     * 品牌编号
     */
    private Long brand;

    private Long firstCategory;

    private Long secondCategory;

    private Long thirdCategory;

    /**
     * 封面图
     */
    private String coverUri;

    /**
     * 视频
     */
    private String videoUri;

    /**
     * 上、下架时间
     */
    private Date shelvedTime;

    /**
     * 上 1，下 0架状态
     */
    private int shelveStatus;

    /**
     * 二维码地址
     */
    private String qrCodeUri;

    private Long skuId;

    private String skuName;

    private String skuDesc;

    private BigDecimal price;

}
