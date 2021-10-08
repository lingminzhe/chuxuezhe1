package com.grgbanking.counter.common.socket.socket.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeeService implements Serializable {

    /**
     * 坐席ID
     */
    private String employeeId;
    /**
     * 用户id
     */
    private String customerId;
    /**
     * userSig
     */
    private String userSig;

}
