package com.component;

import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component
@DependsOn("userService")
public class User {
    public User() {
        System.out.println("[User]无参构造函数");
    }
}
