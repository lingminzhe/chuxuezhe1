package com.grgbanking.counter.grgcloudcounterappmockclient;

import io.socket.client.IO;
import io.socket.client.Socket;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class MockClient {
    Socket socket;

    @PostConstruct
    public void test(){
        register();

        try {
            videoRequest();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void register(){

        String url = "http://10.1.45.25:9843";
        try {
            IO.Options options = new IO.Options();
            options.transports = new String[]{"websocket"};
            //失败重试次数
            options.reconnectionAttempts = 10;
            //失败重连的时间间隔
            options.reconnectionDelay = 1000;
            //连接超时时间(ms)
            options.timeout = 500;
            socket = IO.socket(url, options);
            socket.on(Socket.EVENT_CONNECTING, objects -> System.out.println("client: " + "连接中"));
            socket.on(Socket.EVENT_CONNECT_ERROR, objects -> System.out.println("client: " + "连接失败"));
            socket.on(Socket.EVENT_CONNECT, objects -> System.out.println("client: " + "已连接"));

            socket.connect();


            JSONObject json=new JSONObject();
            json.put("term_schema","app");
            json.put("term_id","00000001");
            socket.emit("register",json);
            //   socket.send("123");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void videoRequest() throws JSONException {

        if(socket!=null){
            socket.on("push_event",objects -> {
                JSONObject jsonObject=(JSONObject)objects[0];
                System.out.println(jsonObject);
            });
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("service_session_id","uuid1234");
            jsonObject.put("service_type","video");
            socket.emit("push_event",jsonObject);
            //   socket.send("123");
        }
    }
}
