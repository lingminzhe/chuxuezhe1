package com.grgbanking.counter.csr.api.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 坐席客服表
 * 
 * @author GrgBanking
 * @date 2021-09-13
 */
@Data
public class GrgCusEmployeeServiceEntity implements Serializable {

	/**
	 * 主键
	 */
	private String id;
	/**
	 * 坐席ID
	 */
	private String employeeId;
	/**
	 * 用户id
	 */
	private String userId;
	/**
	 * 坐席状态
	 */
	private String employeeStatus;
	/**
	 * 业务编码
	 */
	private String busiNo;

}
