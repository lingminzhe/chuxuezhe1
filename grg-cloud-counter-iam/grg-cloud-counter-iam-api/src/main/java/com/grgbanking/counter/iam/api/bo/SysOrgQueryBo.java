package com.grgbanking.counter.iam.api.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: SysOrgBo
 * @Description: 机构传输对象
 * @Date: 2021/8/6 16:09
 * @Author: lggui1
 * @Version: 1.0
 */
@Data
@ApiModel(value = "机构传输对象")
public class SysOrgQueryBo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Long id;
    /**
     * 父id
     */
    private String parentCode;
    /**
     * 地址
     */
    @ApiModelProperty(value = "地址")
    private String address;
    /**
     * 机构名称
     */
    @ApiModelProperty(value = "机构名称")
    private String name;
    /**
     * 机构编码
     */
    @ApiModelProperty(value = "机构编码")
    private String orgCode;

    /**
     * 机构全称
     */
    @ApiModelProperty(value = "机构全称")
    private String fullname;

    /**
     * 国际化编码
     */
    @ApiModelProperty(value = "国际化编码")
    private String i18nCode;

    /**
     * 金融机构编码
     */
    @ApiModelProperty(value = "金融机构编码")
    private String finCode;

    /**
     * 英文名
     */
    @ApiModelProperty(value = "机构状态")
    private String isEnabled;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String description;
    /**
     * 描述
     */
    @ApiModelProperty(value = "区域ID")
    private Long areaId;

    /**
     * 描述
     */
    @ApiModelProperty(value = "机构path列表")
    private List<String> orgPathList;

    /**
     * 描述
     */
    @ApiModelProperty(value = "机构path列表")
    private List<Long> orgIds;


}
