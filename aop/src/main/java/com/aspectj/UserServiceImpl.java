package com.aspectj;

import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl {
    public void test() {
        System.out.println("[UserService][test]");
    }
}
