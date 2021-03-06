package com.grgbanking.counter.bank.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
@ApiModel
public class GrgAccountEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(name = "id", value = "id")
	private Integer id;
	/**
	 * 客户id
	 */
	@NotNull(message = "账户id必须提交")
	@ApiModelProperty(name = "customerId", value = "客户id")
	private Integer customerId;
	/**
	 * 卡号
	 */
	@NotBlank(message = "卡号必须提交")
	@ApiModelProperty(name = "cardNo", value = "卡号")
	private String cardNo;
	/**
	 * 卡密
	 */
	private String cardPwd;
	/**
	 * 开户行
	 */
	@ApiModelProperty(name = "cardBank", value = "开户行")
	private String cardBank;
	/**
	 * 卡类型
	 */
	@ApiModelProperty(name = "cardType", value = "卡类型( 1：借记卡  2：信用卡)")
	private String cardType;

	/**
	 * 开户证件类型
	 */
	@ApiModelProperty(name = "cardCertificateType", value = "开户证件类型")
	private String cardCertificateType;
	/**
	 * 账户签发类型（0：面签； 1：网签； 2：其它）
	 */
	@ApiModelProperty(name = "accountSignType", value = "账户签发类型（0：面签； 1：网签； 2：其它）")
	private Integer accountSignType;
	/**
	 * 账户状态（0：已激活； 1：未激活； 2：已挂失）
	 */
	@ApiModelProperty(name = "accountStatus", value = "账户状态（0：已激活； 1：未激活； 2：已挂失）")
	private Integer accountStatus;
	/**
	 * 锁定状态（0：未锁定； 1：已锁定）
	 */
	private Integer lockStatus;
	/**
	 * 
	 */
	@TableField(fill = FieldFill.INSERT)
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
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateDate;
	/**
	 * 
	 */
	private String updateBy;
	/**
	 * 银行卡绑定状态（0：未绑定；1：已绑定）
	 */
	private String bind;

	/**
	 * 信用卡激活码
	 */
	@ApiModelProperty(name = "cvvCode", value = "信用卡激活码")
	private String cvvCode;

}
