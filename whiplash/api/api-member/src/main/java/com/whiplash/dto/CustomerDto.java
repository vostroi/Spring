package com.whiplash.dto;

import cn.hutool.json.JSONUtil;
import com.whiplash.components.member.bean.Customer;
import com.whiplash.core.commom.util.CommomConstant;
import com.whiplash.core.commom.util.EnumConstant;
import com.whiplash.core.platform.bean.BaseDto;
import com.whiplash.core.platform.bean.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Administrator
 * @date 2021/9/25 17:31
 * @projectName whiplash
 * @title: CustomerDto
 * @description: TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CustomerDto extends BaseDto {

    private String nickName = CommomConstant.STR_EMPTY;

    private EnumConstant.GENDER gender = EnumConstant.GENDER.UNKNOWN;

    private Long mobile;

    private String country = CommomConstant.STR_EMPTY;

    private String province = CommomConstant.STR_EMPTY;

    private String city = CommomConstant.STR_EMPTY;

    private String realName = CommomConstant.STR_EMPTY;

    private String idCode = CommomConstant.STR_EMPTY;

    private String headImg = CommomConstant.STR_EMPTY;

    private EnumConstant.MEMBER_LEVEL level = EnumConstant.MEMBER_LEVEL.NORMAL;

    /**
     * 转换 Customer 到 CustomerDto 简要信息
     * @param customer
     */
    public static CustomerDto fromCustomerBrief(Customer customer) {
        CustomerDto custDto = new CustomerDto();

        if(customer != null){
            custDto = CustomerDto.builder()
                    .nickName(customer.getNickName())
                    .gender(customer.getGender())
                    .headImg(customer.getHeadImg())
                    .build()
            ;

            custDto.setId(customer.getId());
            custDto.setState(customer.getState());

        }
        return custDto;
    }



}
