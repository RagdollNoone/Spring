package com;

import java.util.concurrent.locks.LockSupport;

public class VisibilityTest {
    private boolean flag = true;
    private int count = 0;

    public static void main(String[] args) throws Exception {
        VisibilityTest test = new VisibilityTest();

        Thread threadA = new Thread(() -> test.load(), "Thread A");
        threadA.start();

        Thread.sleep(1000);

        Thread threadB = new Thread(() -> test.refresh(), "Thread B");
        threadB.start();


    }

    public void refresh() {
        flag = false;
        System.out.println(Thread.currentThread().getName() + " modify flag: " + flag);
    }

    public void load() {
        System.out.println(Thread.currentThread().getName() + " start...");

        while (flag) {
            count++;

            //1. jvm层面 storeLoad内存屏障    ===>  x86   lock替代了mfence
            //2. 上下文切换   Thread.yield();

//            UnsafeFactory.getUnsafe().storeFence();

//            Thread.yield();

//            System.out.println(count);

//            LockSupport.unpark(Thread.currentThread());

//            try {
//                Thread.sleep(1);
//            } catch (Exception e) {
//                // do nothing
//            }
        }
    }
}
