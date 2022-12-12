package com;

public class SingletonFactory {
    private volatile static SingletonFactory instance; // 一定要用volatile修饰

    public static SingletonFactory getInstance() {
        if (null == instance) {
            synchronized (SingletonFactory.class) {
                if (null == instance) {
                    instance = new SingletonFactory(); // new操作有指令重排的问题
                }
            }
        }

        return instance;
    }

    public static void main(String[] args) {
        SingletonFactory.getInstance();
    }
}
