package com.framework.proxy;

import com.framework.protocol.http.HttpProtocol;

public class ProtocolFactory {
    public static Protocol getProtocol() {
        String name = System.getProperty("protocolName");

        if (null == name || name.equals("")) {
            name = "http";
        }

        switch (name) {
            case "http":
                return new HttpProtocol();
            case "dubbo":
                return null; // 实现dubbo协议 底层依赖netty
            default:
                break;
        }

        return new HttpProtocol();
    }

}
