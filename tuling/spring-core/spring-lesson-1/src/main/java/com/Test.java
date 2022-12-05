package com;

import com.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        System.out.println("[Test][main]start");
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = (UserService) applicationContext.getBean("userService");

//        testAop(userService);
        testTransaction(userService);

        System.out.println("[Test][main]end");
    }

    private static void testAop(UserService userService) {
        userService.test();
    }

    private static void testTransaction(UserService userService) {
        userService.addUser();
    }

}
