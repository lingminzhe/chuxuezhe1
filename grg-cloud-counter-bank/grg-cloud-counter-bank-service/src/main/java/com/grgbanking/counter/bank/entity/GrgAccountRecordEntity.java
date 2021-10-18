package com.grgbanking.counter.bank.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
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
	 * 账户id
	 */
	@ApiModelProperty(name = "accountId", value = "账户id")
	private Integer accountId;
	/**
	 * 流水号
	 */
	private String serialNumber;
	/**
	 * 业务类型（1：账户密码解锁；2：云挂失；3：账户升级；4：账户降级）
	 */
	@ApiModelProperty(name = "businessType", value = "业务类型（1：账户密码解锁；2：云挂失；3：账户升级；4：账户降级；5：信用卡激活）")
	private Integer businessType;
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
	       // 新增的时候填充数据
	private String updateBy;

}
