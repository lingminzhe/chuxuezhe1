package com.grgbanking.counter.bank.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 银行卡详细表
 * 
 * @author GRGBanking
 * @email ${email}
 * @date 2021-09-03 13:43:55
 */
@Data
@TableName("grg_account")
public class GrgAccountEntity implements Serializable {
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
	 * 卡号
	 */
	private String cardNo;
	/**
	 * 卡密
	 */
	private String cardPwd;
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
