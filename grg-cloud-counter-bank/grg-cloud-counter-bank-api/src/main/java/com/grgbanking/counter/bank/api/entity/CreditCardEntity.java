package com.grgbanking.counter.bank.api.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class CreditCardEntity implements Serializable {

    @NotNull(message = "Id必须提交")
    private String accountId;

    @NotNull(message = "激活码必须提交")
    private String cvvCode;

    @NotNull(message = "userId不能为空")
    private String userId;
}
