package com;

import static com.Const.DEFAULT_PORT;

public class Server {
    private static ServerHandle serverHandle;

    public static void main(String[] args){
        serverHandle = new ServerHandle(DEFAULT_PORT);
        new Thread(serverHandle, "Server").start();
    }
}
