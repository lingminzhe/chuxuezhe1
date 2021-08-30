package com.grgbanking.counter.iam.api.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 分页之系统多语言维护信息
 *
 * @Author:cyfeng2
 * @Date:2021/1/14
 */
@Data
@ApiModel(value = "国际化数据传输对象")
public class SysI18nBo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "应用类别")
    private String appType;

    @ApiModelProperty(value = "数据类型")
    private String dataType;


    @ApiModelProperty(value = "国际化编码")
    private String i18nCode;

    @ApiModelProperty(value = "国际化值")
    private String i18nValue;

    @ApiModelProperty(value = "国际化语言类型")
    private String i18nLanguageType;

}
