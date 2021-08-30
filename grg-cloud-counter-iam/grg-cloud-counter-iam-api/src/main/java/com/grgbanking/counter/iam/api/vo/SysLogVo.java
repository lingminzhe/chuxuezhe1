package com.grgbanking.counter.iam.api.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统日志
 *
 */
@Data
public class SysLogVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    private Long id;

    //操作说明
    @ApiModelProperty(value = "操作说明")
    private String operation;

    //调用方法
    @ApiModelProperty(value = "调用方法")
    private String method;

    //请求参数
    @ApiModelProperty(value = "请求参数")
    private String params;

    //执行时长(毫秒)
    @ApiModelProperty(value = "执行时长(毫秒)")
    private Long time;

    //IP地址
    @ApiModelProperty(value = "IP地址")
    private String ip;

    //创建人
    @ApiModelProperty(value = "创建人")
    private String createdBy;

    //创建时间
    @ApiModelProperty(value = "创建时间")
    private Date creationDate;

    @ApiModelProperty(value = "操作序号")
    private String operationId;

}
