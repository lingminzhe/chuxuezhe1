package com.grgbanking.counter.iam.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 区域
 *
 * @author zzwei6
 * @email
 * @date 2021年1月5日19:38:14
 */
@Data
@ApiModel(value = "区域")
public class SysAreaVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Id不能为空")
    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "结构path")
    private String areaPath;

    @ApiModelProperty(value = "国际码")
    private String i18nCode;

    @ApiModelProperty(value = "区域编码")
    private String areaCode;

    @ApiModelProperty(value = "父编码")
    private String parentCode = "0";

    @ApiModelProperty(value = "区域名称")
    @NotBlank(message = "区域名称不能为空")
    private String name;

    @ApiModelProperty(value = "描述")
    private String description;

    @NotBlank(message = "状态不能为空")
    @ApiModelProperty(value = "是否启用状态；1 启用；0 禁用")
    private String isEnabled;

    @ApiModelProperty(value = "创建时间")
    private Date creationDate;

    @ApiModelProperty(value = "修改时间")
    private Date lastUpdateDate;

    @ApiModelProperty(value = "创建人")
    private String createdBy;

    @ApiModelProperty(value = "修改人")
    private String lastUpdatedBy;

    @ApiModelProperty(value = "操作序号")
    private String operationId;

    private List<SysAreaVo> children = null;
}
