package com.grgbanking.counter.iam.api.entity;

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
 * 客户社交账户认证信息表
 * </p>
 *
 * @author <a href="https://grgbanking.com">grgbanking</a>
 * @since 2021-09-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_social_auth_user")
public class SysSocialAuthUserEntity extends Model<SysSocialAuthUserEntity> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("user_name")
    private String userName;

    /**
     * 客户id
     */
    @TableField("customer_id")
    private Integer customerId;

    /**
     * 登录方式
     */
    @TableField("login_type")
    private String loginType;

    /**
     * 登录账号
     */
    @TableField("login_no")
    private String loginNo;

    /**
     * 0-未锁定，1-已锁定
     */
    @TableField("lock_flag")
    private Integer lockFlag;

    /**
     * 1-可用；0-禁用
     */
    @TableField("enabled")
    private Integer enabled;

    /**
     * 头像
     */
    @TableField("avatar")
    private String avatar;

    @TableField("create_date")
    private LocalDateTime createDate;

    @TableField("create_by")
    private String createBy;

    @TableField("update_date")
    private LocalDateTime updateDate;

    @TableField("update_by")
    private String updateBy;

}
