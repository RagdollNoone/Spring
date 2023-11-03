package com.factoryMethodPattern;

public class FactoryMethodPatternTest {
    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();
        Shape circle = shapeFactory.getObject("0");
        circle.draw();
    }
}
