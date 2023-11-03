package com.classLoad;

public class DynamicLoadTest {
    static {
        System.out.println("[DynamicLoadTest][static]static block start");
    }

    public static void main(String[] args) {
        new A();
        System.out.println("[DynamicLoadTest][main]start");
        B b = null;
    }

    static class A {
        static {
            System.out.println("[A][static]static block start");
        }

        public A() {
            System.out.println("[A][A]start");
        }
    }

    static class B {
        static {
            System.out.println("[B][static]static block start");
        }

        public B() {
            System.out.println("[B][B]start");
        }
    }
}
