package com.grgbanking.counter.csr.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author: Ye Kaitao
 * @create: 2021-09-16
 */
@Data
public class EmployeeCustomerVo {

    private String id;
    /**
     * 坐席ID
     */
    private String employeeId;
    /**
     * 坐席状态
     */
    private volatile String employeeStatus;
    /**
     * 业务编码
     */
    private String busiNo;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;


    /**
     * 用户id
     */
    private String customerId;

    /**
     * 业务操作流水号
     */
    private String busiOptNo;

}
