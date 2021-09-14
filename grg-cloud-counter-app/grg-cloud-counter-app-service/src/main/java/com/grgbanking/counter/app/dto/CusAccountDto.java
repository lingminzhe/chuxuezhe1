package com.grgbanking.counter.app.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CusAccountDto {

    @NotNull(message = "账户id必须提交")
    private String userId;

    private String accountId;

}
