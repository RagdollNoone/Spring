package com.filter;

import com.result.MyResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Activate(group = CommonConstants.CONSUMER)
public class MyDubboExceptionFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        log.info("[MyDubboExceptionFilter][invoke]start");

        Result result;

        try {
            result = invoker.invoke(invocation);
        } catch (Throwable t) {
            MyResult myResult = MyResult.newError();
            myResult.setRetMsg(t.getMessage());

            CompletableFuture c = new CompletableFuture ();
            AsyncRpcResult asyncRpcResult = new AsyncRpcResult(c, invocation);
            asyncRpcResult.setValue(myResult);
            result = asyncRpcResult;
        }

        return result;
    }
}
