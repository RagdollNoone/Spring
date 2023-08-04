package com.framework.protocol.http;

import com.alibaba.fastjson.JSONObject;
import com.framework.protocol.Invocation;
import com.framework.register.LocalRegister;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class HttpServerHandler {
    public void handler(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Invocation invocation = JSONObject.parseObject(req.getInputStream(), Invocation.class);
            String interfaceName = invocation.getInterfaceName();
            Class<?> implClass = LocalRegister.get(interfaceName);
            Method method = implClass.getMethod(invocation.getMethodName(), invocation.getParamTypes());
            String result = (String) method.invoke(implClass.newInstance(), invocation.getParams());

            System.out.println("tomcat: " + result);
            IOUtils.write(result, resp.getOutputStream());
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
