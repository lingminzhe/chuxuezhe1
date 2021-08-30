package com.grgbanking.counter.iam.api.dubbo;


import com.grgbanking.counter.iam.api.bo.UserData;

public interface AuthRemoteService {

    /**
     * 用户登录时查询用户信息的接口
     * @param username
     * @return
     */
    UserData info(String username);

}
