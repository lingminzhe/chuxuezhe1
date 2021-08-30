package com.grgbanking.counter.iam.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @param
 * @return
 */
@Data
@ApiModel("菜单列表信息")
public class SysMenuVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "父编码")
    private String parentCode;

    @ApiModelProperty(value = "编码")
    private String menuCode;

    @ApiModelProperty(value = "结构PATH")
    private String menuPath;

    @ApiModelProperty(value = "应用类型")
    private String appType;

    @ApiModelProperty(value = "资源类型")
    private String type;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "国际化编码")
    private String i18nCode;

    @ApiModelProperty(value = "请求主机")
    private String host;

    @ApiModelProperty(value = "请求端口")
    private String port;

    @ApiModelProperty(value = "请求路径")
    private String path;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "序号")
    private Integer orderNum;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "是否启用")
    private String isEnabled;

    @ApiModelProperty(value = "创建人")
    private String createdBy;

    @ApiModelProperty(value = "创建日期")
    private Date creationDate;

    @ApiModelProperty(value = "修改人")
    private String lastUpdatedBy;

    @ApiModelProperty(value = "修改日期")
    private Date lastUpdateDate;

    @ApiModelProperty(value = "操作序号")
    private String operationId;

    @ApiModelProperty(value = "权限ID集合")
    private List<Long> authorityIdList;

    @ApiModelProperty(value = "权限标识，多个用逗号分割")
    private String authorityMarks;

    @ApiModelProperty(value = "权限ID")
    private Long authorityId;

    @ApiModelProperty(value = "单个权限标识")
    private String authority;
}
