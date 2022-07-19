package com;

import com.request.DemoRequest;

import javax.validation.constraints.NotNull;

public interface IDemoService {
    String invoke(@NotNull DemoRequest request);
}
