package com;

import com.request.DemoRequest;
import com.result.MyResult;

import javax.validation.constraints.NotNull;

public interface IDemoService {
    MyResult<String> invoke(@NotNull DemoRequest request);
}
