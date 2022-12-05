package com.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class UserService {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void test() {
        System.out.println("[UserService][test]start");
    }

    @Transactional
    public void addUser() {
        System.out.println("[UserService][addUser]start");
        jdbcTemplate.execute("insert user values (2, 'dendy', 32)");
        // tryToThrowException(); // 事务失效
        userService.tryToThrowException();
    }

    // 事务失效
    // 检查是否是代理对象在执行当前方法 只要是代理对象 事务就不会失效
    @Transactional(propagation = Propagation.NEVER)
    public void tryToThrowException() {

    }
}
