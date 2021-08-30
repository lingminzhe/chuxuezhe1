package com.grgbanking.counter.iam.constans;

/**
 * 菜单类型
 *
 * @author cyfeng2
 * @email
 * @date 2021年1月25日
 */
public enum MenuType {

    /**
     * 系统
     */
    SYSTEM("-1"),
    /**
     * 目录
     */
    CATALOG("0"),
    /**
     * 菜单
     */
    MENU("1"),
    /**
     * 按钮
     */
    BUTTON("2"),
    /**
     * 系统(最上层菜单)的父ID
     */
    SYSTEM_PARENT("0");

    private String value;

    MenuType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
