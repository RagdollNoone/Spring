package com.framework.protocol.http;

import com.alibaba.fastjson.JSONObject;
import com.framework.protocol.Invocation;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class JdkHttpClient {
    public String send(String hostName, Integer port, Invocation invocation) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http", null, hostName, port, "/", null, null))
                    .POST(HttpRequest.BodyPublishers.ofString(JSONObject.toJSONString(invocation)))
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String result = response.body();

            return result;
        } catch (Throwable t) {

        }

        return null;
    }
}
