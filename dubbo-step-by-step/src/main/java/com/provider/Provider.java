package com.provider;

import com.api.HelloService;
import com.framework.URL;
import com.framework.protocol.http.HttpServer;
import com.framework.register.LocalRegister;
import com.framework.register.RemoteMapRegister;
import com.provider.impl.HelloServiceImpl;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Provider {
    public static void main(String[] args) throws UnknownHostException {
        String interfaceName = HelloService.class.getName();
        URL url = new URL(InetAddress.getLocalHost().getHostAddress(), 8081);

        LocalRegister.register(interfaceName, HelloServiceImpl.class);

//        ZookeeperRegister.regist(interfaceName, url);
//        NettyServer nettyServer = new NettyServer();
//        nettyServer.start(url.getHostname(), url.getPort());

        System.out.println(String.format("success, 成功暴露%s服务，地址为%s", interfaceName, url.toString()));
    }

    public static void test1() {
        // 注册
        URL url = new URL("localhost", 8080);
        RemoteMapRegister.register(HelloService.class.getName(), url);
        LocalRegister.register(HelloService.class.getName(), HelloServiceImpl.class);

        // 启动http
        HttpServer httpServer = new HttpServer();
        httpServer.start(url.getHostname(), url.getPort());
    }

}
