package com.advance.server.asyncpro;

import io.netty.util.NettyRuntime;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class AsyncBusiProcess {
    private static BlockingQueue<Runnable> taskQueue  = new ArrayBlockingQueue<Runnable>(3000);
    private static ExecutorService executorService = new ThreadPoolExecutor(1, NettyRuntime.availableProcessors(),60, TimeUnit.SECONDS,taskQueue);

    public static void submitTask(Runnable task){
        executorService.execute(task);
    }
}
