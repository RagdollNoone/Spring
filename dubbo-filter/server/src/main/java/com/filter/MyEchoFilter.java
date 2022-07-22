package com.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

@Slf4j
@Activate(group = CommonConstants.PROVIDER)
public class MyEchoFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        log.info("[MyEchoFilter][invoke]start");

        RpcContext rpcContext = RpcContext.getContext();
        log.info("url: {}", invoker.getUrl().toFullString());
        log.info("simpleName: {}", invoker.getInterface().getSimpleName());
        log.info("serviceName: {}", invocation.getServiceName());
        log.info("methodName: {}", invocation.getMethodName());

        if (rpcContext.isConsumerSide()) {
            log.info("I'm consumer");
        }

        if (rpcContext.isProviderSide()) {
            log.info("I'm provider");
        }

        return invoker.invoke(invocation);
    }
}
