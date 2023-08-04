package com.framework.protocol.http;

import com.framework.URL;
import com.framework.protocol.Invocation;
import com.framework.proxy.Protocol;

public class HttpProtocol implements Protocol {
    @Override
    public void start(URL url) {
        HttpServer httpServer = new HttpServer();
        httpServer.start(url.getHostname(), url.getPort());
    }

    @Override
    public String send(URL url, Invocation invocation) {
        JdkHttpClient jdkHttpClient = new JdkHttpClient();
        String result = jdkHttpClient.send(url.getHostname(), url.getPort(), invocation);
        return result;
    }
}
