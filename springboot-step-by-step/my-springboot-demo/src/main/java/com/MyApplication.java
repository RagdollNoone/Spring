package com;

import com.annotation.MySpringBootApplication;
import com.bean.MySpringApplication;

@MySpringBootApplication
public class MyApplication {
    public static void main(String[] args) {
        MySpringApplication.run(MyApplication.class);
    }
}
