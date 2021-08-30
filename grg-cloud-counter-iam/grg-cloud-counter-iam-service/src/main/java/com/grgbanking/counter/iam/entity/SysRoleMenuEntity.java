package com.grgbanking.counter.iam.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 角色与菜单对应关系表
 *
 */
@Data
@TableName("SYS_ROLE_MENU")
public class SysRoleMenuEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId
    private Long id;

    /**
     * 用户编号
     */
    private Long roleId;

    /**
     * 菜单编号
     */
    private Long menuId;

    /**
     * 是否负责人
     */
    private String  isLeader;

    /**
     * 是否启用
     */
    private String  isEnabled;
    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private Date creationDate;

    /**
     * 修改人
     */
    private String lastUpdatedBy;

    /**
     * 修改时间
     */
    private Date lastUpdateDate;

    /**
     * 操作序号
     */
    private String operationId;

}
