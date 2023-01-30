package com.controller;

import com.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
//    @Autowired
//    private RestTemplate restTemplate;
//
//    @RequestMapping(value = "/findOrderByUserId/{id}")
//    public Result findOrderByUserId(@PathVariable("id") Integer id) {
//        log.info("根据userId:"+id+"查询订单信息");
//        // restTemplate调用,url写死
//        //String url = "http://localhost:8020/order/findOrderByUserId/"+id;
//        // ribbon实现，restTemplate需要添加@LoadBalanced注解
//        // mall-order  ip:port
//        String url = "http://mall-order/order/findOrderByUserId/"+id;
//
//        Result result = restTemplate.getForObject(url, Result.class);
//        return result;
//    }

    @RequestMapping(value = "/findOrderByUserId/{id}")
    public Result findOrderByUserId(@PathVariable("id") Integer id) {
        log.info("根据userId:"+id+"查询订单信息");
        Result result = new Result();
        return result;
    }
}
