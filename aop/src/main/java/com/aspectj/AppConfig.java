package com.aspectj;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.aspectj")
@EnableAspectJAutoProxy // 开启切面
public class AppConfig {
}
