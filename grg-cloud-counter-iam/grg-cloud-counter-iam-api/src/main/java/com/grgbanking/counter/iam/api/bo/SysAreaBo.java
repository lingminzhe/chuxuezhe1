package com.grgbanking.counter.iam.api.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: SysAreaBo
 * @Description: 区域传输对象
 * @Date: 2021年1月6日08:41:05
 * @Author: zzwei6
 * @Version: 1.0
 */
@Data
@ApiModel(value = "区域传输对象")
public class SysAreaBo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "区域path")
    private String areaPath;

    @ApiModelProperty(value = "国际码")
    private String i18nCode;

    @ApiModelProperty(value = "区域编码")
    private String areaCode;

    @ApiModelProperty(value = "父编码")//上级区域为空，PARENT_CODE为0
    private String parentCode;

    @ApiModelProperty(value = "区域名称")
    private String name;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "是否启用状态；1 启用；0 禁用")
    private String isEnabled;

    @ApiModelProperty(value = "创建人")
    private String createdBy;

    @ApiModelProperty(value = "修改人")
    private String lastUpdatedBy;

    @ApiModelProperty(value = "操作序号")
    private String operationId;

    @ApiModelProperty(value = "父id")
    private String parentId;
}
