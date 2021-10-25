package com.grgbanking.counter.app.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CusAccountDto {

    @NotNull(message = "账户id必须提交")
    @ApiModelProperty(value = "用户名", example = "12345", required = true)
    private String userId;

    @ApiModelProperty(value = "可为空", required = false)
    private String accountId;

    @NotNull(message = "业务状态必须提交")
    @ApiModelProperty(value = "业务状态 1启用 2停用", example = "1", required = true)
    private String busiStatus;
}
