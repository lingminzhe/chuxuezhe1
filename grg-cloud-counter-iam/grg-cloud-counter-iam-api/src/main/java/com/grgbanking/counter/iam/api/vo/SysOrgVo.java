package com.grgbanking.counter.iam.api.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class SysOrgVo implements Serializable {

    private static final long serialVersionUID = 5492536856250019821L;

    /**
     * 主键
     */
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
     * 机构名称
     */
    private String name;
    /**
     * 机构全称
     */
    private String fullname;
    /**
     * 机构编码
     */
    private String orgCode;

    private String address;
    /**
     * 国际化编码
     */
    private String i18nCode;

    /**
     * 英文名
     */
    private String isEnabled;
    /**
     * 描述
     */
    private String description;
    /**
     * 描述
     */
    private Long areaId;
    /**
     * 金融机构编码
     */
    private String finCode;
    /**
     * 区域
     */
    private String areaName;
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

    private List<SysOrgVo> children = null;
}
