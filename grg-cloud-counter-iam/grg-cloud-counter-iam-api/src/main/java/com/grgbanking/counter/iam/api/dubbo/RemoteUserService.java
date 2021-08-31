package com.grgbanking.counter.iam.api.dubbo;


import com.grgbanking.counter.iam.api.dto.UserInfo;

public interface RemoteUserService {

    /**
     * 用户登录时查询用户信息的接口
     * @param username
     * @return
     */
    UserInfo info(String username);

}
