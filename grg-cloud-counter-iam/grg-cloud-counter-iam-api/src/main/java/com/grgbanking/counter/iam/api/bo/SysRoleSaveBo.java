package com.grgbanking.counter.iam.api.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SysRoleSaveBo implements Serializable {

    private static final long serialVersionUID = -6604858914782788392L;

    /**
     * 角色编号
     */
    @ApiModelProperty(value = "角色编号")
    private Long id;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "角色分类")
    private String roleType;

    /**
     * 备注
     */
    @ApiModelProperty(value = "描述")
    private String description;

    /**
     * 是否启用
     */
    private String isEnabled;

    @ApiModelProperty(value = "使用菜单id列表")
    private List<Long> useMenuIdList;

    @ApiModelProperty(value = "分配菜单id列表")
    private List<Long> allocMenuIdList;

    @ApiModelProperty(value = "授权用户列表")
    private List<Long> userIdList;
}
