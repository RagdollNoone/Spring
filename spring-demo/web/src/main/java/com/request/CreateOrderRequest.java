package com.request;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class CreateOrderRequest implements Serializable {
    private static final long serialVersionUID = -6136805379392751343L;

    private String requestNo; // 请求序列号

    private Long productId; // 商品id

    private Long userId; // 用户id
}
