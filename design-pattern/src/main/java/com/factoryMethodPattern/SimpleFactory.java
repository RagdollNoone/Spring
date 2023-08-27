package com.factoryMethodPattern;

public class SimpleFactory {
    public static Product createProduct(String type) {
        if (type.equals("0")) {
            return new ProductA();
        } else if (type.equals("1")) {
            return new ProductB();
        } else {
            return null;
        }
    }
}
