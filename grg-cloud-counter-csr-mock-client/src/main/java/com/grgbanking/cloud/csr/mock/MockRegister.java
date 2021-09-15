package com.grgbanking.cloud.csr.mock;

import io.socket.client.IO;
import io.socket.client.Socket;
import org.json.JSONException;
import org.json.JSONObject;
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
            socket.on(Socket.EVENT_CONNECT, objects -> System.out.println("client: " + "连接成功"));
            socket.on("push_event", objects ->  {
                JSONObject jsonObject=(JSONObject) objects[0];
                JSONObject body = null;
                try {

                    body =jsonObject.optJSONObject("body");
                    if(body!=null){
                        System.out.println(body.get("msg"));
                    }else {
                        body=new JSONObject();
                    }


                    JSONObject head =(JSONObject)  jsonObject.get("head");
                    String serviceType=(String)head.get("tran_code");
                    if(serviceType.equals("video_cmd")){
                        body.put("url","http://liangge.weiwu.com");
                        jsonObject.put("body",body);

                        socket.emit("push_event",jsonObject);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            });
            socket.connect();
            Map<String,Object> map=new HashMap<>();
            Map<String,Object> head=new HashMap<>();
            Map<String,Object> body=new HashMap<>();
            head.put("tran_code","register");
            head.put("user_login_type","counter");
            head.put("user_login_id","001");
            body.put("token_id","csrtokenid");
            map.put("body",body);
            map.put("head",head);

            socket.emit("push_event",map);
            //   socket.send("123");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
