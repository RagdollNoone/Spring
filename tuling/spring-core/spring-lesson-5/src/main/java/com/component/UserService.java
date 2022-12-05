package com.component;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public UserService() {
        System.out.println("[UserService]无参构造函数");
    }
}
