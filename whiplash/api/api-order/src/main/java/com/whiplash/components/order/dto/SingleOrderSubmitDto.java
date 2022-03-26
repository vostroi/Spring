package com.whiplash.components.order.dto;

import com.whiplash.core.platform.bean.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Administrator
 * @date 2021/10/26 14:13
 * @projectName whiplash
 * @title: SingleOrderSubmitDto
 * @description: 用于直接下单 提交订单
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class SingleOrderSubmitDto extends BaseDto {

    private Long custId;

    private Long prodId;

    // 规格
    private Long specsId;

    private Integer skuNum;

}
