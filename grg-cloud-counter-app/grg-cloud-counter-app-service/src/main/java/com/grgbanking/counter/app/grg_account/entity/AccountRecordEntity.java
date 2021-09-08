package com.grgbanking.counter.app.grg_account.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 银行卡业务操作记录表
 * 
 * @author Ye Kaitao
 * @date 2021-08-30
 */
@Data
@TableName("grg_account_info_details")
public class AccountRecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 账户id
	 */
	private Integer accountId;
	/**
	 * 流水号
	 */
	private String serialNumber;
	/**
	 * 业务类型（1：账户密码解锁；2：云挂失；3：账户升级；4：账户降级）
	 */
	private Integer businessType;
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
