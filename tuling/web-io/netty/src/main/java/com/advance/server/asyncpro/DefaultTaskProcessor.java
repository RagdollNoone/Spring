package com.advance.server.asyncpro;

import com.advance.vo.MyMessage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultTaskProcessor implements ITaskProcessor {
    @Override
    public Runnable execAsyncTask(MyMessage msg) {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                log.info("DefaultTaskProcessor模拟任务处理："+msg.getBody());
            }
        };
        return task;
    }
}
