package com.result;

public enum MyResultCode implements IMyResultCode {
    SUCCESS("200", "操作成功"),
    FAIL("500", "系统异常");

    private final String code;
    private final String message;

    MyResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getRetCode() {
        return code;
    }

    @Override
    public String getRetMsg() {
        return message;
    }
}
