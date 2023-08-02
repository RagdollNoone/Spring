package com.redis.component;

import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

public class RedisLockKeepAliveTask implements Runnable {
    private StringRedisTemplate stringRedisTemplate;
    private String lockName;
    private String clientId;
    private long expireTime;

    public RedisLockKeepAliveTask(String lockName, String clientId, long expireTime, StringRedisTemplate stringRedisTemplate) {
        this.lockName = lockName;
        this.clientId = clientId;
        this.expireTime = expireTime;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void run() {
        if (clientId.equals(stringRedisTemplate.opsForValue().get(lockName))) {
            System.out.println("lockName: " + lockName + ", clientId: " + clientId + ", 锁续命...");
            Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockName, clientId, expireTime, TimeUnit.SECONDS); // 锁续命
        } else {
            System.out.println("锁释放");
            RedisLockWatchDog.endWatch();
        }
    }
}
