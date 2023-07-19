package com;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OOMTest {
    public static List<Object> list = new ArrayList<>();

    public static void main(String[] args) {
        int i = 0;
        int j = 0;

        while (true) {
            list.add(new User(i++, UUID.randomUUID().toString()));
            new User(j--, UUID.randomUUID().toString());
        }
    }

    private static class User {
        public int id;
        public String uuid;

        public User(int id, String uuid) {
            this.id = id;
            this.uuid = uuid;
        }
    }
}
