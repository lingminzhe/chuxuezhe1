package com.grgbanking.counter.csr.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 坐席客服表
 * 
 * @author GrgBanking
 * @email ${email}
 * @date 2021-09-13 10:55:49
 */
@Data
@TableName("grg_employee_service")
public class GrgEmployeeServiceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private String id;
	/**
	 * 坐席ID
	 */
	private String employeeId;
	/**
	 * 坐席状态
	 */
	private String employeeType;
	/**
	 * 业务编码
	 */
	private String busiNo;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;

}
