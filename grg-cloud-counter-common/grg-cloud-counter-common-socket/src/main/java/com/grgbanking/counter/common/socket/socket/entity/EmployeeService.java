package com.grgbanking.counter.common.socket.socket.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeeService implements Serializable {

    /**
     * 坐席ID
     */
    private String employee_id;
    /**
     * 用户id
     */
    private String customer_id;
    /**
     * userSig
     */
    private String user_sig;

}
