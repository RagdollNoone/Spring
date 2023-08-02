package com.redis.component;

import java.util.concurrent.*;

public class RedisLockWatchDogThreadPool {
    private static ThreadLocal<ScheduledThreadPoolExecutor> threadLocalExecutor = new ThreadLocal<>();

    public static void beginWatch(Runnable task, long expireTime) {
        ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);
        threadLocalExecutor.set(executor);
        executor.scheduleAtFixedRate(task, expireTime / 3, expireTime / 3, TimeUnit.SECONDS);
    }

    public static void endWatch() {
        threadLocalExecutor.get().shutdown();
        clear();
    }

    public static void clear() {
        threadLocalExecutor.remove();
    }
}
