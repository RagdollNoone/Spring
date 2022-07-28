package com.controller;

import com.OrderService;
import com.alibaba.fastjson.JSON;
import com.model.OrderInfo;
import com.request.CreateOrderRequest;
import com.response.CreateOrderResponse;
import com.result.MyResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("api/spring-demo/")
public class OrderController {
    @Resource
    private OrderService orderService;

    @PostMapping("createOrder")
    public MyResult<CreateOrderResponse> createOrder(@RequestBody CreateOrderRequest request) {
        log.info("[OrderController][createOrder]start, request: {}", JSON.toJSONString(request));
        MyResult<CreateOrderResponse> result = MyResult.newSuccess();

        Map<String, String> params = JSON.parseObject(JSON.toJSONString(request), Map.class);

        OrderInfo data = orderService.createOrder(params);
        CreateOrderResponse response = new CreateOrderResponse();
        response.setOrderNo(data.getOrderNo());
        response.setRequestNo(request.getRequestNo());
        response.setCreateTime(data.getCreateTime());
        result.setData(response);

        log.info("[OrderController][createOrder]end, result: {}", JSON.toJSONString(result));
        return result;
    }
}
