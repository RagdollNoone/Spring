package com.framework.proxy;

import com.framework.URL;
import com.framework.protocol.Invocation;

public interface Protocol {
    void start(URL url);
    String send(URL url, Invocation invocation);
}
