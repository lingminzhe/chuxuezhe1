package com.grgbanking.counter.bank.api.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class CreditCardEntity implements Serializable {

    @NotNull(message = "激活码必须提交")
    private String accountId;

    private String cvvCode;

}
