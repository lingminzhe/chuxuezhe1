package com.grgbanking.counter.iam.api.vo;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.List;

/**
 * 国际化
 *
 * @author cyfeng2
 * @email
 * @date 2021年1月14日
 */
@ApiModel(value = "国际化输出Json")
public class SysI18nDataTypeVo implements Serializable {

    private static final long serialVersionUID = 2355675385062210334L;

    private List<SysI18nDataType> dataTypeList;

    private String dataType;


    public List<SysI18nDataType> getDataTypeList() {
        return dataTypeList;
    }

    public void setDataTypeList(List<SysI18nDataType> dataTypeList) {
        this.dataTypeList = dataTypeList;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    @Override
    public String toString() {
        return "SysI18nDataTypeVo{" +
                "dataTypeList=" + dataTypeList +
                ", dataType='" + dataType + '\'' +
                '}';
    }
}
