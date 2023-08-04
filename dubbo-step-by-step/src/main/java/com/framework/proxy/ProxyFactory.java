package com.framework.proxy;

import com.framework.LoadBalance;
import com.framework.URL;
import com.framework.protocol.Invocation;
import com.framework.protocol.http.JdkHttpClient;
import com.framework.register.RemoteMapRegister;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

// 客户端组件
public class ProxyFactory<T> {
    @SuppressWarnings("unchecked")
    public static <T> T getProxy(final Class<?> interfaceClass) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Invocation invocation = new Invocation(interfaceClass.getName(), method.getName(), method.getParameterTypes(), args);
                try {
                    // 地址写死
                    JdkHttpClient jdkHttpClient = new JdkHttpClient();
                    String result = jdkHttpClient.send("localhost", 8080, invocation);
                    return result;
                } catch (Exception e) {

                }

                return null;
            }
        });
    }

    @SuppressWarnings("unchecked")
    public static <T> T getProxy1(final Class<?> interfaceClass) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Invocation invocation = new Invocation(interfaceClass.getName(), method.getName(), method.getParameterTypes(), args);
                try {
                    // 负载均衡
                    List<URL> urlList = RemoteMapRegister.get(interfaceClass.getName());
                    URL url = LoadBalance.random(urlList);

                    JdkHttpClient jdkHttpClient = new JdkHttpClient();
                    String result = jdkHttpClient.send(url.getHostname(), url.getPort(), invocation); // 地址从本地文件注册中心获取
                    return result;
                } catch (Exception e) {

                }

                return null;
            }
        });
    }

    @SuppressWarnings("unchecked")
    public static <T> T getProxy2(final Class<?> interfaceClass) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 更多的代理增强功能在这里实现

                Invocation invocation = new Invocation(interfaceClass.getName(), method.getName(), method.getParameterTypes(), args);
                try {
                    // 负载均衡
                    List<URL> urlList = RemoteMapRegister.get(interfaceClass.getName());
                    URL url = LoadBalance.random(urlList);

                    JdkHttpClient jdkHttpClient = new JdkHttpClient();
                    String result = jdkHttpClient.send(url.getHostname(), url.getPort(), invocation); // 地址从本地文件注册中心获取
                    return result;
                } catch (Exception e) {
                    return doMock(invocation); // 添加mock容错机制
                }
            }
        });
    }

    private static Object doMock(Invocation invocation) {
        return "容错逻辑";
    }
}
