package com.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkDemo {
    public static void main(String[] args) {
        UserService userService = (UserService) Proxy.newProxyInstance(
                JdkDemo.class.getClassLoader(),
                new Class[]{UserService.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if (method.getName().equals("test")) {
                            System.out.println("[UserService][test]");
                        }
                        return null;
                    }
                }
        );

        userService.test();
        userService.addUser();
    }
}
