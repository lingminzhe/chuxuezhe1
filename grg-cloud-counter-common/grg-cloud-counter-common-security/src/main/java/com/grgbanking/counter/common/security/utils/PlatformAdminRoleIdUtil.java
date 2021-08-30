package com.grgbanking.counter.common.security.utils;

import com.grgbanking.counter.common.core.encrypt.DESUtil;

import javax.annotation.Resource;

/**
 * @description: 获取平台超级管理员角色ID
 * @author: chainos
 * @create: 2020-11-13 13:56
 */
@Resource
public interface PlatformAdminRoleIdUtil {

    String roleId = "BF1FBDA33801E543";

    /**
     * 获取平台超级管理员角色ID
     *
     * @return 平台超级管理员角色ID
     */
    static Long getPlatformAdminRoleId() {
        return Long.valueOf(DESUtil.decodeUserLoggingMsg(roleId));
    }

//    static void main(String[] args){
//        System.out.println(DESUtil.encodeUserLoggingMsg("1"));
//        System.out.println(DESUtil.decodeUserLoggingMsg("BF1FBDA33801E543"));
//    }

}
