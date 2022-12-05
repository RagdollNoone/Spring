package com.component;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Scope("prototype")
@Service
public class OrderService {
    // 无参构造函数
    public OrderService() {
        System.out.println("[OrderService]无参构造函数");
    }

    // 有参构造函数
    public OrderService(UserService userService) {
        System.out.println("[OrderService]有参构造函数");
    }
}
