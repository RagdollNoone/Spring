package com;

import com.component.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        System.out.println("[Test][main]start");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        test1(context);

        System.out.println("[Test][main]end");
    }

    // 条件加载 只有User类被扫描到 UserService才是一个component
    private static void test1(AnnotationConfigApplicationContext context) {
        UserService userService = (UserService) context.getBean("userService");

        System.out.println("[Test][test1]" + userService);
    }
}
