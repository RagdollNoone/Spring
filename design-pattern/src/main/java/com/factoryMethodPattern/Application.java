package com.factoryMethodPattern;

public class Application {
    private Product createProduct(String type) {
        // init
        return SimpleFactory.createProduct(type);
    }

    public Product getObject(String type) {
        Product product = createProduct(type);
        return product;
    }

}
