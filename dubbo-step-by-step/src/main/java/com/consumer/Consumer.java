package com.consumer;

import com.api.HelloService;
import com.framework.protocol.Invocation;
import com.framework.protocol.http.JdkHttpClient;
import com.framework.proxy.ProxyFactory;

public class Consumer {
    public static void main(String[] args) {

//        NettyClient nettyClient = new NettyClient();
//        String result = nettyClient.send("localhost", 8080, invocation);
//        System.out.println(result);

    }

    public static void test1() {
        Invocation invocation = new Invocation(
                HelloService.class.getName(),
                "sayHello",
                new Class[]{String.class},
                new Object[]{"dubbo"});

        JdkHttpClient jdkHttpClient = new JdkHttpClient();
        String result = jdkHttpClient.send("localhost", 8080, invocation);

        System.out.println(result);
    }

    public static void temp () {
        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        String result = helloService.sayHello("dubbo");
        System.out.println(result);
    }

}
