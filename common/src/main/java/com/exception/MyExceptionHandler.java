package com.exception;

import lombok.extern.slf4j.Slf4j;

import java.io.PrintWriter;
import java.io.StringWriter;

@Slf4j
public class MyExceptionHandler {
    private MyExceptionHandler() {}

    public static void handle(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);

        log.info("未知异常, 堆栈信息: {}", sw);
    }
}
