package com.orderserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @RequestMapping("/selectOrderInfoByIdAndUsername")
    public String selectOrderInfoByUserId(long id, String username) {
        return "获取订单信息成功";
    }
}
