package com.grgbanking.counter.bank.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 银行卡业务操作记录表
 * 
 * @author GRGBanking
 * @email ${email}
 * @date 2021-09-03 13:43:56
 */
@Data
@TableName("grg_account_record")
@ApiModel
public class GrgAccountRecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 流水号
	 */
	private String serialNumber;
	/**
	 * 卡号
	 */
	@ApiModelProperty(name = "cardNo", value = "卡号")
	private String cardNo;
	/**
	 * 商户名称
	 */
	@ApiModelProperty(name = "merchant", value = "商户名称")
	private String merchant;
	/**
	 * 金额
	 */
	@ApiModelProperty(name = "amount", value = "金额")
	private BigDecimal amount;
	/**
	 * 交易明细
	 */
	@ApiModelProperty(name = "tranDetail", value = "交易明细")
	private Integer tranDetail;
	/**
	 * 交易状态
	 */
	@ApiModelProperty(name = "txnStatus", value = "交易状态")
	private String txnStatus;
	/**
	 * 
	 */
	@TableField(fill = FieldFill.INSERT)        // 新增的时候填充数据
	private Date createDate;
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

}
