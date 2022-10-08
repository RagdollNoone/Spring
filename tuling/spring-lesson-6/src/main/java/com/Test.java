package com;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        System.out.println("[Test][main]start");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);



        System.out.println("[Test][main]end");
    }

    private static void test1() {

    }
}
