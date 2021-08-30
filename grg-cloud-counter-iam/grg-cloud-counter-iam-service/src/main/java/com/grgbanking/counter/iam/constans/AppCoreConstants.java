package com.grgbanking.counter.iam.constans;

import lombok.AllArgsConstructor;
import lombok.Getter;

public interface AppCoreConstants {
    
    /**
     * 默认密码
     */
    String DEFAULT_PWD = "bcb15f821479b4d5772bd0ca866c00ad5f926e3580720659cc80d39c9d09802a";

    /**
     * 锁定次数, >= 3 锁定
     */
    Integer LOCK_NUM = 3;

    /**
     * 锁定时间
     */
    Long LOCK_TIME = 15L;

    String USER_LOGIN_PREFIX = "ibank:platform:login:locked:";
    String USER_LOGIN_LOCKED = USER_LOGIN_PREFIX + "%s";
    String DEL_LOGIN_LOCKED = USER_LOGIN_PREFIX + "*";
    
    String USER_AREA_TREE = "ibank:platform:%s:area:tree";
    String DEL_AREA_TREE = "ibank:platform:*:area:tree";

    String USER_ORG_ALL = "ibank:platform:%s:org:all";
    String DEL_ORG_ALL = "ibank:platform:*:org:all";

    String USER_ORG_TREE = "ibank:platform:%s:org:tree";
    String DEL_ORG_TREE = "ibank:platform:*:org:tree";

    String USER_ORG_LIST = "ibank:platform:%s:org:list";
    String DEL_ORG_LIST = "ibank:platform:*:org:list";

    String USER_ROLE_LIST = "ibank:platform:%s:role:list";
    String DEL_ROLE_LIST = "ibank:platform:*:role:list";
    
    String USER_MENU_USE = "ibank:platform:%s:menu:use";
    String USER_MENU_ALLOC = "ibank:platform:%s:menu:alloc";
    String DEL_MENU_ALL = "ibank:platform:*:menu:*";

    String USER_AUTHORITY_LIST = "ibank:platform:%s:authority:list";
    String DEL_AUTHORITY_LIST = "ibank:platform:*:authority:list";

    String APP_I18N_LIST = "ibank:platform:i18n:list:%s:%s";
    String DEL_I18N_LIST = "ibank:platform:i18n:list:*:*";

    String APP_DICT_LIST = "ibank:platform:dict:list:%s";
    String DEL_DICT_LIST = "ibank:platform:dict:list:*";
    String CODE_DICT_LIST = "ibank:platform:dict:list:%s";

    /**
     * sys_user:type
     * 用户类型
     * */
    @Getter
    @AllArgsConstructor
    enum UserType{
        WORKTICKET("workticket","工单用户");
        private String code;
        private String value;
    }

    /**
     * isLeader
     * 是否负责人
     * */
    @Getter
    @AllArgsConstructor
    enum LeaderType{
        IS_LEADER ("1","管理机构/维护管理员"),
        DIS_LEADER ("0","所属机构/维护工程师");
        private String code;
        private String value;
    }

    /**
     * 导入数据状态
     * */
    @Getter
    @AllArgsConstructor
    enum StatusType{
        ALL ("0","全部"),
        SUCCESS ("1","成功"),
        FAULT ("2","失败");
        private String code;
        private String value;
    }

    /**
     * Excel业务导入数据类型
     * */
     @AllArgsConstructor
     @Getter
     enum DataType{
       NUMBER ("0","数字类型"),
       STRING("1","字符类型"),
       DATE1("2","日期类型(yyyy-MM-dd HH:mm:ss)"),
       DATE2("3","日期类型(yyyy-MM-dd)"),
       DATE3("4","日期类型(HH:mm:ss)");
       private String code;
       private String value;
    }
    
}
