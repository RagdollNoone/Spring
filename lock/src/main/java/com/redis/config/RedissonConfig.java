package com.redis.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {
    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useClusterServers()
                .setScanInterval(2000)
                .addNodeAddress("redis://192.168.20.129:6379")
                .addNodeAddress("redis://192.168.20.132:6379")
                .addNodeAddress("redis://192.168.20.133:6379");

        RedissonClient redissonClient = Redisson.create(config);

        return redissonClient;
    }
}
