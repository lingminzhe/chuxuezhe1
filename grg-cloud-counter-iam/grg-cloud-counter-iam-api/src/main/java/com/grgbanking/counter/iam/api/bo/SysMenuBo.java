package com.grgbanking.counter.iam.api.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单管理
 *
 * @author MARK xx@grgbanking.com
 */
@Data
@ApiModel(value = "系统菜单传输对象")
public class SysMenuBo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "父编码")
    private String parentCode;

    @ApiModelProperty(value = "编码")
    private String menuCode;


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

    @ApiModelProperty(value = "是否启用")
    private String isEnabled;

    @ApiModelProperty(value = "权限ID集合")
    private List<Long> authorityIdList;

}
