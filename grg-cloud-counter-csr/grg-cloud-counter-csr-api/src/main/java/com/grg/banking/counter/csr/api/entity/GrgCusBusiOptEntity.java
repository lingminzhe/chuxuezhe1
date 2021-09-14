package com.grg.banking.counter.csr.api.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class GrgCusBusiOptEntity implements Serializable {

    /**
     * 主键
     */
    private String id;
    /**
     * 业务编码
     */
    @ApiModelProperty("业务编码")
    private String busiNo;
    /**
     * 坐席ID
     */
    @ApiModelProperty("坐席ID")
    private String userId;
    /**
     * 客户ID
     */
    @ApiModelProperty("客户ID")
    private String customerId;
    /**
     * 业务办理状态
     */
    @ApiModelProperty("业务办理状态")
    private String busiOptStatus;
    /**
     * 业务操作流水号
     */
    @ApiModelProperty("业务操作流水号")
    private String busiOptNo;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

}
