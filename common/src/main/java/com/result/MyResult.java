package com.result;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class MyResult<T> implements Serializable {
    private static final long serialVersionUID = 6668783805993751979L;

    private String retCode;
    private String retMsg;
    private boolean success;
    private T data;

    public MyResult() {}

    public MyResult(String retCode, String retMsg, boolean success, T data) {
        this.retCode = retCode;
        this.retMsg = retMsg;
        this.success = success;
        this.data = data;
    }

    public static <T> MyResult<T> newSuccess() {
        return new MyResult(MyResultCode.SUCCESS.getRetCode(), MyResultCode.SUCCESS.getRetMsg(), true, null);
    }

    public static <T> MyResult<T> newError() {
        return new MyResult(MyResultCode.FAIL.getRetCode(), MyResultCode.FAIL.getRetMsg(), false, null);
    }
}
