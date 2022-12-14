package com;

import org.openjdk.jol.info.ClassLayout;

public class ObjectSizeTest {
    public static void main(String[] args) {
        Object obj = new Object();
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());

        Model model = new Model();
        System.out.println(ClassLayout.parseInstance(model).toPrintable());
    }

    private static class Model {
        private long p;
    }
}
