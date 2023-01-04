package com;

import java.util.Scanner;

import static com.Const.DEFAULT_SERVER_IP;
import static com.Const.DEFAULT_PORT;

public class Client {
    private static ClientHandle clientHandle;

    public static void main(String[] args) throws Exception {
        start();
        Scanner scanner = new Scanner(System.in);
        while(sendMsg(scanner.next()));
    }

    public static void start(){
        clientHandle = new ClientHandle(DEFAULT_SERVER_IP, DEFAULT_PORT);
        new Thread(clientHandle, "client").start();
    }

    //向服务器发送消息
    public static boolean sendMsg(String msg) throws Exception{
        clientHandle.sendMsg(msg);
        return true;
    }

}
