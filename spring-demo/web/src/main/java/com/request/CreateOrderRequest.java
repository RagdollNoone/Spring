package com.request;

import com.request.model.PayerInfo;
import com.request.model.ProductInfo;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class CreateOrderRequest implements Serializable {
    private static final long serialVersionUID = -6136805379392751343L;

    private String requestNo; // 请求id

    private ProductInfo product; // 商品信息

    private PayerInfo payer; // 买家信息
}
