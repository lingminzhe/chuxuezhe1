package com.grgbanking.counter.iam.api.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: SysBizConfBo
 * @Description: 系统业务配置传输对象
 * @Date: 2020/12/21
 * @Author: cyfeng
 * @Version: 1.0
 */
@Data
@ApiModel(value = "系统业务配置传输对象")
public class SysBizConfBo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 客户端id
     */
    @ApiModelProperty(value = "客户端id")
    private Long id;

    /**
     * 应用类型
     */
    @ApiModelProperty(value = "应用类型")
    private String appType;


    /**
     * 业务键
     */
    @ApiModelProperty(value = "业务键")
    private String bizCode;

    /**
     * 业务值
     */
    @ApiModelProperty(value = "业务值")
    private String bizInfo;


    /**
     * 启用状态
     */
    @ApiModelProperty(value = "启用状态")
    private String isEnabled;

}
