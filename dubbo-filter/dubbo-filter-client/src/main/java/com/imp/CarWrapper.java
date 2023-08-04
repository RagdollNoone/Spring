package com.imp;

import com.component.Driver;

public class CarWrapper implements Car {
    private Car car;

    public CarWrapper(Car car) {
        this.car = car;
    }

    @Override
    public void test(Driver driver) {
        System.out.println("car wrapper");
        car.test(driver);
    }
}
