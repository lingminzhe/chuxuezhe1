package com.grgbanking.counter.iam.constans;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author lggui1
 * @Date 2021/1/6
 * @Description
 **/
@Getter
@AllArgsConstructor
public enum Operate {

    ENABLE("1", "启用"),

    DISABLE("0", "停用"),

    LEADER("1", "可分配"),

    DISLEADER("0", "可使用"),

    // 父组织为空用0填充即最顶级组织
    TOP_ORG_PARENT("0", "顶级组织"),

    YES("1", "是"),

    NO("0", "否"),

    LOCKING("1", "锁定"),

    UNLOCKED("0", "未锁定");

    /**
     * 值
     */
    private String code;

    /**
     * 描述
     */
    private String description;

    public static String code(String code) {
        Operate[] Operates = values();
        for (Operate operate : Operates) {
            if (operate.code().equals(code)) {
                return operate.code();
            }
        }
        return null;
    }

    public static String description(String code) {
        Operate[] Operates = values();
        for (Operate Operate : Operates) {
            if (Operate.code().equals(code)) {
                return Operate.description();
            }
        }
        return null;
    }

    public String code() {
        return code;
    }

    public String description() {
        return description;
    }

}
