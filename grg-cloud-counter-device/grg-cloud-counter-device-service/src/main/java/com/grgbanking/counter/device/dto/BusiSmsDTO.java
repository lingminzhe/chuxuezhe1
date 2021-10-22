package com.grgbanking.counter.device.dto;

import lombok.Data;

/**
 * @author: Ye Kaitao
 * @create: 2021-10-19
 */
@Data
public class BusiSmsDTO {
    /**
     * 手机号
     */
    private String mobile;

    /**
     * 业务号
     */
    private String busiNo;
}
