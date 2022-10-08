package com.component;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

@Component
public class Order implements DisposableBean {

    // 或者不实现接口
    // 为方法添加@PreDestroy注解
    @Override
    public void destroy() throws Exception {
        System.out.println("[Order][destroy]start");
    }
}
