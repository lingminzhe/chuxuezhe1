package com.grgbanking.counter.bank.api.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class GrgCusAccountEntity implements Serializable {

    /**
     *
     */
    private Integer id;
    /**
     * 客户id
     */
    @NotNull(message = "账户id必须提交")
    private Integer customerId;
    /**
     * 卡号
     */
    @NotBlank(message = "卡号必须提交")
    private String cardNo;
    /**
     * 卡密
     */
    private String cardPwd;
    /**
     * 开户行
     */
    private String cardBank;
    /**
     * 开户证件类型
     */
    private String cardCertificateType;
    /**
     * 账户签发类型（0：面签； 1：网签； 2：其它）
     */
    private Integer accountSignType;
    /**
     * 账户状态（0：已激活； 1：未激活； 2：已挂失）
     */
    private Integer accountStatus;
    /**
     * 锁定状态（0：未锁定； 1：已锁定）
     */
    private Integer lockStatus;
    /**
     *
     */
    private Date createDate;
    /**
     * 卡片等级（1： 一类卡； 2：二类卡）
     */
    private Integer cardLevel;
    /**
     *
     */
    private String createBy;
    /**
     *
     */
    private Date updateDate;
    /**
     *
     */
    private String updateBy;

}
