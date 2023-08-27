package com.factoryMethodPattern;

// 变化的是越来越多的product类
// 不变的是product的行为
public class FactoryMethodPatternTest {
    public static void main(String[] args) {
        Application application = new Application();
        Product product = application.getObject("0");
        product.method();
    }
}
