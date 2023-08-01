package com.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibDemo {
    public static void main(String[] args) {
        final UserServiceImpl target = new UserServiceImpl();

        Enhancer enhancer = new Enhancer();
//        enhancer.setUseCache(false);
        enhancer.setSuperclass(UserServiceImpl.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                if (method.getName().equals("test")) {
                    System.out.println("before test");
                    method.invoke(target, objects);
                    System.out.println("after test");
                    return null;
                }

                return method.invoke(target, objects);
            }
        });

        UserServiceImpl userServiceImpl = (UserServiceImpl) enhancer.create();
        userServiceImpl.test();
        userServiceImpl.addUser();
    }
}
