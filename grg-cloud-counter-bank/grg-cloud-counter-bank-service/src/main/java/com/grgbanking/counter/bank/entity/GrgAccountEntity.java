package com.grgbanking.counter.bank.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 银行卡详细表
 * </p>
 *
 * @author <a href="https://grgbanking.com">grgbanking</a>
 * @since 2021-09-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("grg_account")
public class GrgAccountEntity extends Model<GrgAccountEntity> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Integer id;

    /**
     * 客户id
     */
    @TableField("customer_id")
    private Integer customerId;

    /**
     * 卡号
     */
    @TableField("card_no")
    private String cardNo;

    /**
     * 卡密
     */
    @TableField("card_pwd")
    private String cardPwd;

    /**
     * 开户证件类型
     */
    @TableField("card_certificate_type")
    private String cardCertificateType;

    /**
     * 账户签发类型（0：面签； 1：网签； 2：其它）
     */
    @TableField("account_sign_type")
    private Boolean accountSignType;

    /**
     * 账户状态（0：已激活； 1：未激活； 2：已挂失）
     */
    @TableField("account_status")
    private Boolean accountStatus;

    /**
     * 锁定状态（0：未锁定； 1：已锁定）
     */
    @TableField("lock_status")
    private Boolean lockStatus;

    @TableField("create_date")
    private LocalDateTime createDate;

    /**
     * 卡片等级（1： 一类卡； 2：二类卡）
     */
    @TableField("card_level")
    private Boolean cardLevel;

    @TableField("create_by")
    private String createBy;

    @TableField("update_date")
    private LocalDateTime updateDate;

    @TableField("update_by")
    private String updateBy;


}
