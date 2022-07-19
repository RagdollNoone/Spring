package com.demo.service;

import com.spring.annotation.Autowired;
import com.spring.annotation.Component;
import com.spring.annotation.Scope;
import com.spring.inter.BeanNameAware;
import com.spring.inter.InitializingBean;

@Component
@Scope("prototype")
public class UserService
implements UserServiceInterface, InitializingBean, BeanNameAware {

    @Autowired
    private OrderService orderService;

    @AutoSetValue("zhoushanhong")
    private String author;

    @Override
    public void print() {
        System.out.println("[UserService][print]start");
    }

    @Override
    public void printOrderService() {
        System.out.println("[UserService][printOrderService]" + orderService);
    }

    @Override
    public void printAuthor() {
        System.out.println("[UserService][printAuthor]" + author);
    }

    @Override
    public void setName(String beanName) {
        System.out.println("[UserService][setName]" + beanName);
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("[UserService][afterPropertiesSet]初始化逻辑");
    }
}
