package com.provider.impl;

import com.api.HelloService;

public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        int exception = 1 / 0;
        return "执行服务实现类" + name;
    }
}
