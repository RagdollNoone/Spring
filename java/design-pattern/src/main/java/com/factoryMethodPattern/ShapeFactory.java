package com.factoryMethodPattern;

public class ShapeFactory {
    public Shape getObject(String type) {
        switch (type) {
            case "0":
                return new Circle();
            case "1":
                return new Rectangle();
            case "2":
                return new Square();
            default:
                return null;
        }
    }
}
