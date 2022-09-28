package com;

import com.component.*;
import org.springframework.beans.SimpleTypeConverter;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.io.Resource;

import java.util.Collections;

public class Test {
    public static void main(String[] args) throws Exception {
        System.out.println("[Test][main]start");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

//        test1(context);
//        test2(context);
//        test3();
//        test4();
//        test5(context);
//        test6(context);
//        test7(context);
        test8(context);

        System.out.println("[Test][main]end");
    }

    // 通过手写beanDefinition的方式创建bean
    private static void test1(AnnotationConfigApplicationContext context) {
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
        beanDefinition.setBeanClass(UserService.class);
        beanDefinition.setScope("prototype");
        context.registerBeanDefinition("userService", beanDefinition);

        UserService userService = (UserService) context.getBean("userService");
        userService.test();
    }

    // 通过beanDefinitionReader的方式创建bean(比beanDefinition的方式更抽象)
    private static void test2(AnnotationConfigApplicationContext context) {
        AnnotatedBeanDefinitionReader annotatedBeanDefinitionReader = new AnnotatedBeanDefinitionReader(context);
        annotatedBeanDefinitionReader.register(User.class);

        User user = (User) context.getBean("user");
        user.test();
    }

    // ClassPathBeanDefinitionScanner代替AppConfig的ComponentScan注解
    private static void test3() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.refresh();

        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(context);
        int scanNum = scanner.scan("com.component");

        System.out.println("[Test][test3]scanNum: " + scanNum);

        Order order = (Order) context.getBean("order");
        order.test();
    }

    // 完全由beanFactory创建bean 证明ApplicationContext是一个beanFactory
    private static void test4() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
        beanDefinition.setBeanClass(UserService.class);

        beanFactory.registerBeanDefinition("userService", beanDefinition);

        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.test();
    }

    // 资源获取 在源码中 服务于扫描逻辑
    private static void test5(AnnotationConfigApplicationContext context) throws Exception {
        Resource resource = context.getResource("classpath:README.md");
        System.out.println("[Test][test5]content length: " + resource.contentLength());
        System.out.println("[Test][test5]url: " + resource.getURL());
    }

    // 把字符串转成特定的类型 应用java原生技术 需要打开AppConfig代码
    private static void test6(AnnotationConfigApplicationContext context) {
        AdminUserService adminUserService = (AdminUserService) context.getBean("adminUserService");
        adminUserService.printAdminUserName();
    }

    // 把字符串转成特定的类型 应用spring技术 需要打开AppConfig代码
    private static void test7(AnnotationConfigApplicationContext context) {
        AdminUserService adminUserService = (AdminUserService) context.getBean("adminUserService");
        adminUserService.printAdminUserName();
    }

    // 使用TypeConverter的方式实现把字符串转成特定的类型
    private static void test8(AnnotationConfigApplicationContext context) throws Exception {
        SimpleTypeConverter typeConverter = new SimpleTypeConverter();
        // 方式 1
        typeConverter.registerCustomEditor(AdminUser.class, new StringToAdminUserPropertyEditor());

        // 方式 2
//        ConversionServiceFactoryBean conversionServiceFactoryBean = new ConversionServiceFactoryBean();
//        conversionServiceFactoryBean.setConverters(Collections.singleton(new StringToAdminUserConverter()));
//        typeConverter.setConversionService(conversionServiceFactoryBean.getObject());

        AdminUserService adminUserService = (AdminUserService) context.getBean("adminUserService");
        adminUserService.printAdminUserName();
    }
}
