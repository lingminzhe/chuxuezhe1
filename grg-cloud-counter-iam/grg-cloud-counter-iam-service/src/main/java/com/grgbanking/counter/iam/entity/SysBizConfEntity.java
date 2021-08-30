package com.grgbanking.counter.iam.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统业务配置表
 */
@Data
@TableName("SYS_BIZ_CONF")
public class SysBizConfEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 客户端id
     */
    @TableId
    private Long id;
    /**
     * 应用类型
     */
    private String appType;
    /**
     * 业务键
     */
    private String bizCode;
    /**
     * 业务值
     */
    private String bizInfo;
    /**
     * 启用状态
     */
    private String isEnabled;

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
