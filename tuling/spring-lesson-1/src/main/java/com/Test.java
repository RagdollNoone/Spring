package com;

import com.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.test();
    }
}
