package com.redis.controller;

import com.redis.component.RedisLockKeepAliveTask;
import com.redis.component.RedisLockWatchDogThreadPool;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class StockController {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    private static String lockName = "lock:product_101";

    @RequestMapping("/deductStock")
    public String deductStock() {
        String result = redisDeductStock();
//        String result = redissonDeductStock();
        System.out.println(result);

        return result;
    }

    private String redisDeductStock() {
        long expireTime = 10;
        String clientId = UUID.randomUUID().toString();
        Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockName, clientId, expireTime, TimeUnit.SECONDS);

        if (null == result || !result) {
            return "error_code";
        }

        // 添加锁续命任务
        RedisLockWatchDogThreadPool.beginWatch(new RedisLockKeepAliveTask(lockName, clientId, expireTime, stringRedisTemplate), expireTime);

        try {
            doDeductStock();
        } catch (Throwable t) {
            // do nothing
        } finally {
            RedisLockWatchDogThreadPool.clear(); // 清空threadLocal变量

            if (clientId.equals(stringRedisTemplate.opsForValue().get(lockName))) {
                stringRedisTemplate.delete(lockName);
            }
        }

        return "redis deductStock success";
    }

    private String redissonDeductStock() {
        RLock redissonLock = redissonClient.getLock(lockName); //获取锁对象
        redissonLock.lock();  //加分布式锁

        try {
            doDeductStock();
        } catch (Throwable t) {
            // do nothing
        } finally {
            redissonLock.unlock(); //解锁
        }

        return "redisson deductStock success";
    }

    private void doDeductStock() throws InterruptedException {
        int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
        if (stock > 0) {
            Thread.sleep(11000);
            int realStock = stock - 1;
            stringRedisTemplate.opsForValue().set("stock", realStock + "");
            System.out.println("扣减成功，剩余库存:" + realStock);
        } else {
            System.out.println("扣减失败，库存不足");
        }
    }
}
