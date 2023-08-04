package com.framework.register;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.framework.URL;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoteMapRegister {
    private static Map<String, List<URL>> REGISTER = new HashMap<>();

    public static void register(String interfaceName, URL url) {
        List<URL> list = REGISTER.get(interfaceName);
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(url);

        REGISTER.put(interfaceName, list);
        saveFile();
    }

    public static List<URL> get(String interfaceName) {
        getFile();
        List<URL> list = REGISTER.get(interfaceName);
        return list;
    }

    // 本地文件模拟注册中心
    private static void saveFile() {
        File f = new File("./serverList.txt");
        OutputStream out = null;
        BufferedWriter bw = null;
        String serverList = JSONObject.toJSONString(REGISTER);

        try {
            if (f.exists()) {
                f.delete();
            }

            f.createNewFile();
            out = new FileOutputStream(f);
            bw = new BufferedWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8));
            bw.write(serverList);
            bw.flush();
            bw.close();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private static void getFile() {
        try {
            InputStream is = new FileInputStream("./serverList.txt");
            int size = is.available();
            byte[] bytes = new byte[size];
            is.read(bytes);
            REGISTER = JSONObject.parseObject(new String(bytes), new TypeReference<Map<String, List<URL>>>(){});
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
