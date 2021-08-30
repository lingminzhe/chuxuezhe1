package com.grgbanking.counter.iam.controller;

import com.alibaba.fastjson.JSONObject;
import com.grgbanking.counter.iam.entity.SysUserEntity;
import com.grgbanking.counter.iam.service.SysUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
public class RemoteSysMenuServiceTest {

    @Autowired
    private SysUserService sysUserService;

    @Test
    public void list() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://127.0.0.1:9051/sysUri/getList";

        JSONObject params = new JSONObject();
        params.put("uri", "sy");
        JSONObject jsonobject = restTemplate.postForObject(url, params, JSONObject.class);
    }

    @Test
    public void queryByUsername(){
        SysUserEntity ylqiang = sysUserService.getUserByUsername("ylqiang");
        System.out.println(ylqiang);
    }
}
