package com.grgbanking.counter.device.vo;

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

    @ApiModelProperty(value = "座席id")
    private String createUser;
    /**
     * 身份证正面   BASE64
     */
    @ApiModelProperty(value = "身份证正面")
    private String file1;

    @ApiModelProperty(value = "身份证反面")
    private String file2;


}
