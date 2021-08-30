package com.grgbanking.counter.iam.api.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class SysRoleListBo implements Serializable {

    private static final long serialVersionUID = -6601511570935532679L;

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "角色类型")
    private String roleType;

    @ApiModelProperty(value = "是否启用")
    private String isEnabled;

    @ApiModelProperty(value = "描述信息")
    private String description;
}
