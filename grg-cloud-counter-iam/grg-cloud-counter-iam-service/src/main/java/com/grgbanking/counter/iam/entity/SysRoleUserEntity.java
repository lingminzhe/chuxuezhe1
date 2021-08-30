package com.grgbanking.counter.iam.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 角色用户关系表
 *
 */
@Data
@TableName("SYS_ROLE_USER")
public class SysRoleUserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键(ID)
     */
    @TableId
    private Long id;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     *创建人
     */
    private String createdBy;
    /**
     * 创建时间
     */

    private Date creationDate;

    /**
     *修改人
     */
    private String lastUpdatedBy;

    /**
     *修改时间
     */
    private Date lastUpdateDate;

    /**
     *操作序号
     */
    private String operationId;

}
