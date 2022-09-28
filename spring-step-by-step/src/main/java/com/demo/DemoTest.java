package com.demo;

import com.demo.service.UserService;
import com.demo.service.UserServiceInterface;
import com.spring.ApplicationContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DemoTest {
    private static final String USER_SERVICE_BEAN_NAME = "userService";

    public static void main(String[] args) {
        ApplicationContext context = new ApplicationContext(AppConfig.class);

        // 不同的测试案例
        test1(context); // 针对scope注解的测试 原型是不同的对象 单例是同一个对线
//        test2(context);
//        test3(context);
    }

    // 测试scope为singleton
    // 测试scope为prototype
    private static void test1(ApplicationContext context) {
        log.info("{}", context.getBean(USER_SERVICE_BEAN_NAME));
        log.info("{}", context.getBean(USER_SERVICE_BEAN_NAME));
        log.info("{}", context.getBean(USER_SERVICE_BEAN_NAME));
    }

    // 测试Autowired
    private static void test2(ApplicationContext context) {
        UserService userService = (UserService) context.getBean(USER_SERVICE_BEAN_NAME);
        userService.print();
        userService.printOrderService();
    }

    // 测试切面
    private static void test3(ApplicationContext context) {
        UserServiceInterface userService = (UserServiceInterface) context.getBean(USER_SERVICE_BEAN_NAME);
        userService.print();
        userService.printOrderService();
        userService.printAuthor();
    }
}
