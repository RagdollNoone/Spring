package com.bean;

import com.inter.WebServer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import java.util.Map;

public class MySpringApplication {
    public static void run(Class clazz){
        // Spring容器
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(clazz);
        applicationContext.refresh();

        WebServer webServer = getWebServer(applicationContext);
        webServer.start();
    }

    public static WebServer getWebServer(WebApplicationContext applicationContext){
        Map<String, WebServer> beansOfType = applicationContext.getBeansOfType(WebServer.class);

        if (beansOfType.isEmpty()) {
            throw new NullPointerException("not found web server");
        }

        if (beansOfType.size() > 1) {
            throw new IllegalStateException("more than one web server");
        }

        return beansOfType.values().stream().findFirst().get();
    }
}
