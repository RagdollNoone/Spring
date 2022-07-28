package com.request.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProductInfo {
    private String name; // 名称

    private String no; // 产品标识号

    private String amount; // 金额
}
