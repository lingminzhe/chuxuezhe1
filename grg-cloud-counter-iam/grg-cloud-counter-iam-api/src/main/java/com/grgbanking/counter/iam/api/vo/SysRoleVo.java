package com.grgbanking.counter.iam.api.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class SysRoleVo implements Serializable {

    private static final long serialVersionUID = 2153085903977229444L;

    /**
     * 角色ID
     */
    @ApiModelProperty(value = "ID")
    private Long id;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    private String name;

    /**
     * 备注
     */
    @ApiModelProperty(value = "描述")
    private String description;

    /**
     * 角色分类
     */
    @NotBlank(message = "角色分类不能为空")
    @ApiModelProperty(value = "角色分类")
    private String roleType;
    /**
     * 是否启用
     */
    private String isEnabled;
    /***创建人*/
    private String createdBy;
    private Date creationDate;
    private String lastUpdatedBy;
    private Date lastUpdateDate;
    private String operationId;

    @ApiModelProperty(value = "使用菜单id列表")
    private List<Long> useMenuIdList;

    @ApiModelProperty(value = "分配菜单id列表")
    private List<Long> allocMenuIdList;

    @ApiModelProperty(value = "授权用户列表(当前用户有权限看到)")
    List<Long> userIdList;

    @ApiModelProperty(value = "授权用户列表(当前用户无权限看到)")
    List<Long> unmanageUserIdList;
}
