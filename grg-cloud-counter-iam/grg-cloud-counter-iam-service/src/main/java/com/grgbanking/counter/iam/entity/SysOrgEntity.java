package com.grgbanking.counter.iam.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 机构用户：机构
 *
 * @author lggui1
 * @date 2021年1月6日
 */
@Data
@TableName("SYS_ORG")
public class SysOrgEntity implements Serializable {

    private static final long serialVersionUID = 6840927627239359787L;

    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 父id
     */
    private String parentCode;

    /**
     * 结构path
     */
    private String orgPath;
    /**
     * I18N国际化值
     */
    private String i18nCode;
    /**
     * 机构编码
     */
    private String orgCode;

    /**
     * 金融机构编码
     */
    private String finCode;

    /**
     * 机构名称
     */
    private String name;

    /**
     * 机构全称
     */
    private String fullname;

    /**
     * 地址
     */
    private String address;

    /**
     * 区域id
     */
    private Long areaId;

    /**
     * 区域名称
     */
    @TableField(exist = false)
    private String areaName;

    /**
     * 停用0/启用1
     */
    private String isEnabled;
    /**
     * 描述
     */
    private String description;
    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private Date creationDate;
    /**
     * 最后修改人
     */
    private String lastUpdatedBy;
    /**
     * 最后修改时间
     */
    private Date lastUpdateDate;

    /**
     * 操作id
     */
    private String operationId;

}
