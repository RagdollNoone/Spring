package com.provider;

import com.api.HelloService;
import com.framework.URL;
import com.framework.protocol.http.HttpServer;
import com.framework.proxy.Protocol;
import com.framework.proxy.ProtocolFactory;
import com.framework.register.LocalRegister;
import com.framework.register.RemoteMapRegister;
import com.provider.impl.HelloServiceImpl;

import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

public class Provider {
    public static void main(String[] args) {
        URL url = new URL("localhost", 8080);
        RemoteMapRegister.register(HelloService.class.getName(), url);
        LocalRegister.register(HelloService.class.getName(), HelloServiceImpl.class);

//        test1(url);
//        test3(url);
        test4(url);
//        rwTest();
    }

    // 没有注册到注册中心 数据都在本地
    public static void test1(URL url) {
        // 启动http
        HttpServer httpServer = new HttpServer();
        httpServer.start(url.getHostname(), url.getPort());
    }

    // serverList数据在文件中
    public static void test3(URL url) {
        // 启动http
        Protocol protocol = ProtocolFactory.getProtocol();
        protocol.start(url);
    }

    // java spi
    public static void test4(URL url) {
        // 启动http
        ServiceLoader<Protocol> serviceLoader = ServiceLoader.load(Protocol.class);
        Iterator<Protocol> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            Protocol protocol = iterator.next();
            protocol.start(url);
        }
    }

    public static void rwTest() {
        URL url = new URL("localhost", 8080);
        RemoteMapRegister.register(HelloService.class.getName(), url);
        List<URL> serverList = RemoteMapRegister.get(HelloService.class.getName());
    }
}
