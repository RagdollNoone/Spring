package com;

import com.imp.Car;
import com.request.DemoRequest;
import com.result.MyResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CallTest {
    @DubboReference(timeout = 10000)
    private IDemoService service;

    @Test
    public void callTest() {
        DemoRequest request = new DemoRequest();
        request.setSubject("test");

        MyResult<String> result = service.invoke(request);
        log.info("{}", result);
    }

    @Test
    public void extensionLoaderTest() {
        ExtensionLoader<Car> extensionLoader = ExtensionLoader.getExtensionLoader(Car.class);
        Car car = extensionLoader.getExtension("car");
        car.test(null);
    }
}
