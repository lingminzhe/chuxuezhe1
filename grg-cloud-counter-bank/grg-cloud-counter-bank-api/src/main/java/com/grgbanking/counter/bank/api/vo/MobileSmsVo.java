package com.grgbanking.counter.bank.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: Ye Kaitao
 * @create: 2021-10-11
 */
@ApiModel(value = "手机验证")
@Data
public class MobileSmsVo implements Serializable {

    /**
     * 手机号
     */

    @ApiModelProperty(value = "手机号")
    private String mobile;
    /**
     * 验证码
     */
    @ApiModelProperty(value = "验证码")
    private String code;

}
