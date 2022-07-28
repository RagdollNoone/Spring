package com.controller;

import com.IResponse;
import com.exception.MyException;
import com.exception.MyExceptionHandler;
import com.result.MyResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class AdviceController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = Throwable.class)
    public MyResult<? extends IResponse> exceptionHandler(Throwable t) {
        MyResult<? extends IResponse> result = MyResult.newError();

        if (t instanceof MyException) {
            MyException exception = (MyException) t;
            result.setRetMsg(exception.getMsg());
        } else {
            MyExceptionHandler.handle(t);
            result.setRetMsg("未知异常");
        }

        return result;
    }

}
