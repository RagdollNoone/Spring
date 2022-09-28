package com.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Order {
    public void test() {
        System.out.println("[Order][test]start");
    }
}
