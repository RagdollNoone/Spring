package com.impl;

import com.IDemoService;
import com.alibaba.fastjson.JSON;
import com.request.DemoRequest;
import com.result.MyResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

@Slf4j
@DubboService
public class DemoServiceImpl implements IDemoService {

    @Override
    public MyResult<String> invoke(DemoRequest request) {
        log.info("[DemoServiceImpl][invoke]request: {}", JSON.toJSONString(request));

        MyResult<String> result = MyResult.newSuccess();
        result.setData("call DemoServiceImpl invoke success!");

        return result;
    }
}
