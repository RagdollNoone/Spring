package com;

import com.component.OrderService;
import com.component.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        System.out.println("[Test][main]start");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

//        test1(context);
//        test2(context);
        test3(context);

        System.out.println("[Test][main]end");
    }

    // 如何在getBean时调用有参构造函数
    private static void test1(AnnotationConfigApplicationContext context) {
        OrderService orderService1 = (OrderService) context.getBean("orderService");
        OrderService orderService2 = (OrderService) context.getBean("orderService", new UserService());
    }

    // bean创建时的依赖关系
    private static void test2(AnnotationConfigApplicationContext context) {

    }

    // bean销毁的应用
    private static void test3(AnnotationConfigApplicationContext context) {
        context.registerShutdownHook();
//        context.close();
    }

}
