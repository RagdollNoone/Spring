package com.impl;

import com.IDemoService;
import com.alibaba.fastjson.JSON;
import com.request.DemoRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

@Slf4j
@DubboService
public class DemoServiceImpl implements IDemoService {

    @Override
    public String invoke(DemoRequest request) {
        log.info("[DemoServiceImpl][invoke]request: {}", JSON.toJSONString(request));
        return "call DemoServiceImpl invoke success!";
    }
}
