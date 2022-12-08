package com;

import java.util.ArrayList;
import java.util.Iterator;

public class FailFastTest {
    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("Michael", 31));
        users.add(new User("Dendy", 32));

        System.out.println(users);

        // fast fail example
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getName().equals("Michael")) {
                users.add(new User("Park", 30)); // 能触发ConcurrentModifyException
                users.remove(0); // 不能触发ConcurrentModifyException
            }
        }

        // correct way
        users.removeIf(user -> user.name.equals("Michael"));

        System.out.println(users);
    }

    private static class User{
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }
}
