package com.grgbanking.counter.iam.api.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 密码表单
 *
 * @author MARK xx@grgbanking.com
 */
@Data
@ApiModel(value = "密码")
public class SysUserPasswordBo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 原密码
     */
    @ApiModelProperty(value = "原密码", required = true)
    @NotBlank(message = "原密码密码不能为空")
    private String password;

    /**
     * 新密码
     */
    @ApiModelProperty(value = "新密码", required = true)
    @NotBlank(message = "新密码不能为空")
    private String newPassword;

    @ApiModelProperty(value = "账号", required = true)
    @NotBlank(message = "账号不能为空")
    private String username;

}
