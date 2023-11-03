package com.classLoad;

import com.sun.crypto.provider.DESKeyFactory;

public class ClassLoaderTest {
    public static void main(String[] args) {
        System.out.println(String.class.getClassLoader());
        System.out.println(DESKeyFactory.class.getClassLoader().getClass().getName());
        System.out.println(ClassLoaderTest.class.getClassLoader());

        System.out.println();

        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        ClassLoader extClassLoader = appClassLoader.getParent();
        ClassLoader bootClassLoader = extClassLoader.getParent();
        System.out.println("appClassLoader is: " + appClassLoader);
        System.out.println("extClassLoader is: " + extClassLoader);
        System.out.println("bootClassLoader is: " + bootClassLoader);

        System.out.println();

        System.out.println("bootClassLoader加载以下文件:");
        System.out.println(System.getProperty("java.ext.dirs"));

        System.out.println();

        System.out.println("appClassLoader加载以下文件:");
        System.out.println(System.getProperty("java.class.path"));
    }
}
