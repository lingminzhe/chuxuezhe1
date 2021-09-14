package com.grg.banking.counter.csr.api.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class GrgCusBusiInfoEntity implements Serializable {

    /**
     * 主键
     */
    private String id;
    /**
     * 业务类别
     */
    private String busiType;
    /**
     * 业务编码
     */
    private String busiNo;
    /**
     * 业务名称
     */
    private String busiName;
    /**
     * 业务状态
     */
    private String busiStatus;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

}
