package com.grgbanking.counter.iam.api.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 权限标识信息
 *
 * @Author:cyfeng2
 * @Date:2021/1/14
 */
@Data
@ApiModel(value = "权限标识信息")
public class SysAuthorityBo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "所属服务名")
    private String appName;

    @ApiModelProperty(value = "所属服务描述")
    private String appDesc;

    @ApiModelProperty(value = "所属模块名")
    private String moduleName;

    @ApiModelProperty(value = "所属模块描述")
    private String moduleDesc;


    @ApiModelProperty(value = "权限标识")
    private String authority;

    @ApiModelProperty(value = "权限标识描述")
    private String description;

    @ApiModelProperty(value = "所属类名")
    private String className;

    @ApiModelProperty(value = "所属方法名")
    private String methodName;

    @ApiModelProperty(value = "完整方法名")
    private String fullMethodName;

    @ApiModelProperty(value = "启用状态")
    private String isEnabled;

}
