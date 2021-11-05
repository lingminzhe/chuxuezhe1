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
    private String id;

    /**
     * 用户名
     */
    @TableField("user_name")
    private String userName;

    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 客户id
     */
    @TableField("customer_id")
    private Integer customerId;

    /**
     * 手机号
     */
    @TableField("phone")
    private String phone;

    /**
     * 微信登录openId
     */
    @TableField("wx_openid")
    private String wxOpenid;

    /**
     * 微信小程序openId
     */
    @TableField("mini_openid")
    private String miniOpenid;

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
