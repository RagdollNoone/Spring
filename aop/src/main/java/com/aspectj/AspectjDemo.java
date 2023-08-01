package com.aspectj;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AspectjDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        UserServiceImpl userServiceImpl = (UserServiceImpl) applicationContext.getBean("userServiceImpl");
        userServiceImpl.test();
    }
}
