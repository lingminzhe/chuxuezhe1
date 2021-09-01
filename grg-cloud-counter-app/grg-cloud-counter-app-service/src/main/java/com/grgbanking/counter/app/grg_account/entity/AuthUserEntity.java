package com.grgbanking.counter.app.grg_account.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户认证信息表
 * 
 * @author Ye Kaitao
 * @email ${email}
 * @date 2021-09-01 09:08:03
 */
@Data
@TableName("grg_auth_user")
public class AuthUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 客户id
	 */
	private Integer customerId;
	/**
	 * 登录方式
	 */
	private String loginType;
	/**
	 * 登录账号
	 */
	private String loginNo;
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
