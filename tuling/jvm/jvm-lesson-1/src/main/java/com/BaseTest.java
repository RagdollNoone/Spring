package com;

public class BaseTest {
    public static final int initData = 666;
    public static User user = new User();

    public int compute() {
        int a = 1;
        int b = 2;
        return (a + b) * 10;
    }

    public static void main(String[] args) {
        BaseTest test = new BaseTest();
        System.out.println(test.compute());
    }

}
