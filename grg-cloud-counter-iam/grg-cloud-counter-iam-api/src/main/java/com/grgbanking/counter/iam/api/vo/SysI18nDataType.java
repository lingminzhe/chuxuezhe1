package com.grgbanking.counter.iam.api.vo;

import java.io.Serializable;

/**
 * @param
 * @return
 */
public class SysI18nDataType implements Serializable {

    private static final long serialVersionUID = 8150919460703014617L;

    private String i18nCode;

    private String i18nValue;

    //字典项接口用到
    private String i18nLanguageType;

    public String getI18nCode() {
        return i18nCode;
    }

    public void setI18nCode(String i18nCode) {
        this.i18nCode = i18nCode;
    }

    public String getI18nValue() {
        return i18nValue;
    }

    public void setI18nValue(String i18nValue) {
        this.i18nValue = i18nValue;
    }

    public String getI18nLanguageType() {
        return i18nLanguageType;
    }

    public void setI18nLanguageType(String i18nLanguageType) {
        this.i18nLanguageType = i18nLanguageType;
    }

    @Override
    public String toString() {
        return "SysI18nDataType{" +
                "i18nCode='" + i18nCode + '\'' +
                ", i18nValue='" + i18nValue + '\'' +
                ", i18nLanguageType='" + i18nLanguageType + '\'' +
                '}';
    }
}
