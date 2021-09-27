package com.grgbanking.counter.iam.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 客户社交账户认证信息表
 * 
 * @author GrgBanking
 * @email ${email}
 * @date 2021-09-27 11:25:44
 */
@Data
@TableName("sys_social_auth_user")
public class SysSocialAuthUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 昵称
	 */
	private String nickName;
	/**
	 * 客户id
	 */
	private Integer customerId;
	/**
	 * 手机号
	 */
	private String phone;
	/**
	 * 微信登录openId
	 */
	private String wxOpenid;
	/**
	 * 微信小程序openId
	 */
	private String miniOpenid;
	/**
	 * 0-未锁定，1-已锁定
	 */
	private Integer lockFlag;
	/**
	 * 1-可用；0-禁用
	 */
	private Integer enabled;
	/**
	 * 头像
	 */
	private String avatar;
	/**
	 * 
	 */
	private Date createDate;
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
