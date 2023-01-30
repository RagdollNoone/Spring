package com.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String userId;

    private String commodityCode; // 商品编号

    private Integer count;

    private Integer amount;
}
