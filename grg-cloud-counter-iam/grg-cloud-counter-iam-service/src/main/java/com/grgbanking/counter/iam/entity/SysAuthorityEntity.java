package com.grgbanking.counter.iam.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 权限标识
 *
 */
@Data
@TableName("sys_authority")
public class SysAuthorityEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;

    /**
     * 权限标识
     */
    private String authority;
    /**
     * 所属服务名称
     */
    private String appName;
    /**
     * 所属服务描述
     */
    private String appDesc;
    /**
     * 类名
     */
    private String className;
    /**
     * 方法名
     */
    private String methodName;
    /**
     * 完整方法名
     */
    private String fullMethodName;
    /**
     * 所属模块
     */
    private String moduleName;
    /**
     * 所属模块描述
     */
    private String moduleDesc;
    /**
     * 描述
     */
    private String description;
    /**
     * 是否启用(0:停用;1:启用)
     */
    private String isEnabled;
    private String createdBy;
    private Date creationDate;
    private String lastUpdatedBy;
    private Date lastUpdateDate;
    private String operationId;

}
