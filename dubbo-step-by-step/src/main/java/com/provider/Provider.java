package com.provider;

import com.api.HelloService;
import com.framework.URL;
import com.framework.protocol.http.HttpServer;
import com.framework.register.LocalRegister;
import com.framework.register.RemoteMapRegister;
import com.provider.impl.HelloServiceImpl;

import java.util.List;

public class Provider {
    public static void main(String[] args) {
        test1();
//        rwTest();
    }

    public static void test1() {
        // 没有注册到注册中心 数据都在本地
        URL url = new URL("localhost", 8080);
        RemoteMapRegister.register(HelloService.class.getName(), url);
        LocalRegister.register(HelloService.class.getName(), HelloServiceImpl.class);

        // 启动http
        HttpServer httpServer = new HttpServer();
        httpServer.start(url.getHostname(), url.getPort());
    }

    public static void rwTest() {
        URL url = new URL("localhost", 8080);
        RemoteMapRegister.register(HelloService.class.getName(), url);
        List<URL> serverList = RemoteMapRegister.get(HelloService.class.getName());
    }
}
