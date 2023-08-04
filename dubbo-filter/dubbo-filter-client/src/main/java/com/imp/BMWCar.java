package com.imp;

import com.component.Driver;
import org.apache.dubbo.common.extension.Activate;

@Activate(value = "test")
public class BMWCar implements Car {
    @Override
    public void test(Driver driver) {
        System.out.println("bmw car");
    }
}
