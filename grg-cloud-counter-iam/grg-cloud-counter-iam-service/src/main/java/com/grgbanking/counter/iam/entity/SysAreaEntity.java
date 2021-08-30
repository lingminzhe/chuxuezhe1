package com.grgbanking.counter.iam.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 系统区域表
 *
 * @author zzwei6
 * @email
 * @date 2021-01-05 18:44:06
 */
@Data
@TableName("sys_area")
public class SysAreaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 结构path
     */
    private String areaPath;

    /**
     * 国际码
     */
    private String i18nCode;

    /**
     * 区域编码
     */
    private String areaCode;

    /**
     * 父编码
     */
    private String parentCode;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 是否启用
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
