package com.builderPattern;

public class BuilderPatternTest {
    public static void main(String[] args) {
        Product product = new Product.Builder()
                .productName("productName")
                .companyName("companyName")
                .part1("part1")
                .build();

        System.out.println(product);
    }
}
