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
    @ApiModelProperty(value = "卡号", required = true)
    private String cardNo;
    /**
     * 卡密
     */
    @ApiModelProperty(value = "卡密")
    private String cardPwd;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "账户状态（1：激活； 2：挂失）修改银行卡状态必填、卡密校验不需要填", required = true)
    private Integer accountStatus;

}
