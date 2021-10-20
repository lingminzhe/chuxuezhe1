package com.grgbanking.counter.bank.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 *
 */
@ApiModel(value = "银行卡验证")
@Data
public class BankCardVo implements Serializable {

    /**
     * 卡号
     */

    @ApiModelProperty(value = "卡号")
    private String cardNo;
    /**
     * 卡密
     */
    @ApiModelProperty(value = "卡密")
    private String cardPwd;

}
