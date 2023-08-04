package com.imp;

import com.component.Driver;
import org.apache.dubbo.common.extension.SPI;

@SPI
public interface Car {
    void test(Driver driver);
}
