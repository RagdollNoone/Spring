package com.consumer;

import com.api.HelloService;
import com.framework.protocol.Invocation;
import com.framework.protocol.http.JdkHttpClient;
import com.framework.proxy.ProxyFactory;

public class Consumer {
    public static void main(String[] args) {
//        test();
//        test1();
//        test2();
        test4();
    }

    // 原始实现
    // 写死构造 没有从注册中心拿数据
    // ip 端口写死 没有来自注册中心
    public static void test() {
        Invocation invocation = new Invocation(
                HelloService.class.getName(),
                "sayHello",
                new Class[]{String.class},
                new Object[]{"dubbo"});

        JdkHttpClient jdkHttpClient = new JdkHttpClient();
        String result = jdkHttpClient.send("localhost", 8080, invocation);

        System.out.println(result);
    }

    // api接口增强 拥有传输数据的能力
    // 服务提供方的ip地址 端口从本地注册中心文件当中获取
    // 最终要把动态代理对象加入到spring容器当中去
    public static void test1() {
        HelloService helloService = ProxyFactory.getProxy1(HelloService.class);
        String result = helloService.sayHello("dubbo");
        System.out.println(result);
    }

    // 添加了mock机制
    // 打开HelloServiceImpl类的 1/0 注释
    public static void test2() {
        HelloService helloService = ProxyFactory.getProxy2(HelloService.class);
        String result = helloService.sayHello("dubbo");
        System.out.println(result);
    }

    // spi
    public static void test4() {
        HelloService helloService = ProxyFactory.getProxy4(HelloService.class);
        String result = helloService.sayHello("dubbo");
        System.out.println(result);
    }

}
