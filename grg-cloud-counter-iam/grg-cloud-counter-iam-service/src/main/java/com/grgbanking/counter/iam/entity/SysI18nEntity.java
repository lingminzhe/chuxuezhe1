package com.grgbanking.counter.iam.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统多语言维护信息表
 */
@Data
@TableName("SYS_I18N")
public class SysI18nEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;

    private String appType;

    private String dataType;

    private String i18nCode;

    private String i18nLanguageType;

    private String i18nValue;

    private String createdBy;

    private Date creationDate;

    private String lastUpdatedBy;

    private Date lastUpdateDate;

    private String operationId;

}
