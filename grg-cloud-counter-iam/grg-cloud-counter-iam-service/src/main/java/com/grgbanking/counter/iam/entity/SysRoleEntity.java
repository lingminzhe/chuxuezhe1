package com.grgbanking.counter.iam.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 机构用户：系统角色
 *
 * @author lggui1
 */
@Data
@TableName("sys_role")
public class SysRoleEntity implements Serializable {

    private static final long serialVersionUID = -2042124112858903056L;

    /**
     * 角色编号
     */
    @TableId
    private Long id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色分类：mybatis.mappers.mysql.mybatis.mappers.oracle.system:系统用户，workManager:工单管理员，workEngineer:工单维护工程师。
     */
    private String roleType;

    /**
     * 备注
     */
    private String description;
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

}
