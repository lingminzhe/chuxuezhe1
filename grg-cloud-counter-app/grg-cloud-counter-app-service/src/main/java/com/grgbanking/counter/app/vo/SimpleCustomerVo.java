package com.grgbanking.counter.app.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: Ye Kaitao
 * @create: 2021-10-12
 */
@Data
public class SimpleCustomerVo {
    @ApiModelProperty(value = "客户id")
    private String customerId;

    @ApiModelProperty(value = "客户姓名")
    private String name;

    @ApiModelProperty(value = "客户身份证号")
    private String identifyNumber;

//    @ApiModelProperty(value = "sessionId")
//    private String sessionId;

}
