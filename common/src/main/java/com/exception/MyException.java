package com.exception;

import com.result.MyResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class MyException extends RuntimeException {
    private static final long serialVersionUID = 655530942065041425L;

    private final String code;
    private final String msg;

    public MyException(MyResultCode resultCode) {
        this(resultCode.getRetCode(), resultCode.getRetMsg());
    }

    public MyException(String msg) {
        this(null, msg);
    }

    public MyException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;

        log.info("{}", msg);
    }
}
