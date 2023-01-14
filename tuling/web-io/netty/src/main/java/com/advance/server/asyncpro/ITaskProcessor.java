package com.advance.server.asyncpro;

import com.advance.vo.MyMessage;

public interface ITaskProcessor {
    Runnable execAsyncTask(MyMessage msg);
}
