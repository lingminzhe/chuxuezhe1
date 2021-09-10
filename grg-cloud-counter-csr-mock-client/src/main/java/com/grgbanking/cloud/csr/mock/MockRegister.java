package com.grgbanking.cloud.csr.mock;

import io.socket.client.IO;
import io.socket.client.Socket;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class MockRegister {

    @PostConstruct
    public void register(){

        String url = "http://10.1.45.25:9844";
        try {
            IO.Options options = new IO.Options();
            options.transports = new String[]{"websocket"};
            //失败重试次数
            options.reconnectionAttempts = 10;
            //失败重连的时间间隔
            options.reconnectionDelay = 1000;
            //连接超时时间(ms)
            options.timeout = 500;
            final Socket socket = IO.socket(url, options);
            socket.on(Socket.EVENT_CONNECTING, objects -> System.out.println("client: " + "连接中"));
            socket.on(Socket.EVENT_CONNECT_ERROR, objects -> System.out.println("client: " + "连接失败"));
            socket.connect();
            Map<String,String> map=new HashMap<>();
            map.put("term_schema","counter");
            map.put("term_id","001");
            socket.emit("register",map);
            //   socket.send("123");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
