package com.demo.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionHandler {
    public static void handle(Throwable throwable) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        throwable.printStackTrace(printWriter);
        System.out.println(stringWriter.toString());
    }
}
