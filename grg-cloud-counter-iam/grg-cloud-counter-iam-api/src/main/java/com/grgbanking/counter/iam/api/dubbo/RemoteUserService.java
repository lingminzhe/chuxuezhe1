package com.grgbanking.counter.iam.api.dubbo;


import com.grgbanking.counter.iam.api.dto.UserInfo;

public interface RemoteUserService {

    /**
     * 系统用户登录时查询用户信息的接口
     * @param username
     * @return
     */
    UserInfo info(String username);

    /**
     * 通过社交账号、手机号查询用户、角色信息
     * @param inStr TYPE@CODE
     * @return
     */
    UserInfo social(String inStr);

}
