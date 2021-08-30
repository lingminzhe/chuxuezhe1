package com.grgbanking.counter.iam.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 系统配置：数据字典
 *
 * @author zzwei6
 * @email
 * @date 2021年1月14日18:39:26
 */
@Data
@TableName("SYS_DICT")
public class SysDictEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId
    private Long id;

    /**
     * 应用类别
     */
    private String appType;

    /**
     * 代码类型
     */
    private String codeType;

    /**
     * 代码值
     */
    private String codeValue;

    /**
     * 代码值名称
     */
    private String codeValueName;

    /**
     * 国际化编码
     */
    private String i18nCode;

    /**
     * 顺序
     */
    private Integer codeOrder;

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
