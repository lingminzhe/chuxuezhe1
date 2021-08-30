package com.grgbanking.counter.iam.api.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author:mcyang
 * @Date:2020/11/6 10:52 上午
 */
@Data
@ApiModel(value = "权限菜单传输对象")
public class SysMenuAuthorityBo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 权限ID
     */
    @ApiModelProperty(value = "权限ID")
    private Long authorityId;

    /**
     * 菜单ID
     */
    @ApiModelProperty(value = "菜单ID")
    private Long menuId;

}
