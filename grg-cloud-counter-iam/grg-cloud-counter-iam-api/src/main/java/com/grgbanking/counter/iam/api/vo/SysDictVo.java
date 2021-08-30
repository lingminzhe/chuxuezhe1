package com.grgbanking.counter.iam.api.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 数据字典出参
 */
@Data
public class SysDictVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
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
    private String codeOrder;

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

    private List<SysDictVo> children = null;

    private List<SysI18nDataType> i18n;
}
