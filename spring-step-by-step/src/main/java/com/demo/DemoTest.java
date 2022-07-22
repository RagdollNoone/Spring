package com.demo;

import com.demo.service.UserService;
import com.demo.service.UserServiceInterface;
import com.exception.ExceptionHandler;
import com.spring.ApplicationContext;

public class DemoTest {
    public static void main(String[] args) {
        ApplicationContext context = new ApplicationContext(AppConfig.class);

        // 不同的测试案例
//        test1(context);
//        test2(context);
//        test3(context);
//        test4(context);
        test5(context);
    }

    // 测试scope为singleton
    private static void test1(ApplicationContext context) {
        System.out.println(context.getBean("userService"));
        System.out.println(context.getBean("userService"));
        System.out.println(context.getBean("userService"));
    }

    // 测试scope为prototype
    private static void test2(ApplicationContext context) {
        System.out.println(context.getBean("userService"));
        System.out.println(context.getBean("userService"));
        System.out.println(context.getBean("userService"));
    }

    // 测试Autowired
    private static void test3(ApplicationContext context) {
        UserService userService = (UserService) context.getBean("userService");
        userService.print();
        userService.printOrderService();
    }

    // 测试assert
    private static void test4(ApplicationContext context) {
        try {
            ApplicationContext localContext = new ApplicationContext(null);
        } catch (Exception e) {
            ExceptionHandler.handle(e);
        }
    }

    // 测试切面
    private static void test5(ApplicationContext context) {
        UserServiceInterface userService = (UserServiceInterface) context.getBean("userService");
        userService.print();
        userService.printOrderService();
        userService.printAuthor();
    }
}
