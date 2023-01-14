package com.advance.utils;

import java.util.concurrent.atomic.AtomicLong;

public class MakeMsgID {
    private static AtomicLong msgId = new AtomicLong(1);

    public static long getID(){
        return msgId.getAndIncrement();
    }
}
