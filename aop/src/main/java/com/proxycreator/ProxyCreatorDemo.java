package com.proxycreator;

import com.aspectj.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ProxyCreatorDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
    }
}
