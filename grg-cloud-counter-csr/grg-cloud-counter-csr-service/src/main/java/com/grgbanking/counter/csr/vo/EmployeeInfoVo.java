package com.grgbanking.counter.csr.vo;

import lombok.Data;

/**
 * @author: Ye Kaitao
 * @create: 2021-09-16
 */
@Data
public class EmployeeInfoVo {
    /**
     * 主键
     */
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
     * 用户名
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 部门ID
     */
    private Long deptId;


    /**
     * 0-未锁定，1-已锁定
     */
    private Integer lockFlag;

    /**
     * 1-可用；0-禁用
     */
    private Integer enabled;
}
