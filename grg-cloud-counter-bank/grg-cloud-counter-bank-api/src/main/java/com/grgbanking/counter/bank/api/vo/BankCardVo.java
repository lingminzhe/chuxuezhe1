package com.grgbanking.counter.bank.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
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
    @ApiModelProperty(value = "卡号", required = true)
    private String cardNo;
    /**
     * 卡密
     */
    @ApiModelProperty(value = "卡密")
    private String cardPwd;

    @ApiModelProperty(value = "账户状态（1：激活； 2：挂失）", required = true)
    private Integer cardStatus;

}
