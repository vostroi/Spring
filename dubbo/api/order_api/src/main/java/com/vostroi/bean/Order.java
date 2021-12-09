package com.vostroi.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Administrator
 * @title: Order
 * @projectName sp_dbo
 * @description: TODO
 * @date 2020/5/1315:09
 */
@Data
@AllArgsConstructor
@ToString
public class Order implements Serializable {
    private String id;

    private String addr;

    private String customerName;

}
