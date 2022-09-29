package com;

import com.component.User;
import com.component.UserFactoryBean;
import com.component.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        System.out.println("[Test][main]start");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

//        test1(context);
        test2(context);

        System.out.println("[Test][main]end");
    }

    // 条件加载 只有User类被扫描到 UserService才是一个component
    private static void test1(AnnotationConfigApplicationContext context) {
        UserService userService = (UserService) context.getBean("userService");

        System.out.println("[Test][test1]" + userService);
    }

    // UserFactoryBean实现的是SmartFactoryBean 并且isEagerInit返回true 则user在启动时就会被创建
    // UserFactoryBean实现的是FactoryBean则user在getBean时才会被创建
    private static void test2(AnnotationConfigApplicationContext context) {
        User user = (User) context.getBean("userFactoryBean");
        System.out.println("[Test][test2]" + user);

        UserFactoryBean userFactoryBean = (UserFactoryBean) context.getBean("&userFactoryBean");
        System.out.println("[Test][test2]" + userFactoryBean);
    }

}
