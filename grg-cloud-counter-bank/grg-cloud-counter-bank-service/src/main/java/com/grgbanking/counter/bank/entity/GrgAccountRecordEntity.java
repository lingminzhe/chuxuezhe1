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
 * 银行卡业务操作记录表
 * </p>
 *
 * @author <a href="https://grgbanking.com">grgbanking</a>
 * @since 2021-09-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("grg_account_record")
public class GrgAccountRecordEntity extends Model<GrgAccountRecordEntity> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Integer id;

    /**
     * 账户id
     */
    @TableField("account_id")
    private Integer accountId;

    /**
     * 流水号
     */
    @TableField("serial_number")
    private String serialNumber;

    /**
     * 业务类型（1：账户密码解锁；2：云挂失；3：账户升级；4：账户降级）
     */
    @TableField("business_type")
    private Integer businessType;

    @TableField("create_date")
    private LocalDateTime createDate;

    @TableField("create_by")
    private String createBy;

    @TableField("update_date")
    private LocalDateTime updateDate;

    @TableField("update_by")
    private String updateBy;

}
