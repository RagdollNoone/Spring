package com;

import com.request.DemoRequest;
import com.result.MyResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CallTest {
    @DubboReference
    private IDemoService service;

    @Test
    public void callTest() {
        DemoRequest request = new DemoRequest();
        request.setSubject("test");

        MyResult<String> result = service.invoke(request);
        log.info("{}", result);
    }
}
