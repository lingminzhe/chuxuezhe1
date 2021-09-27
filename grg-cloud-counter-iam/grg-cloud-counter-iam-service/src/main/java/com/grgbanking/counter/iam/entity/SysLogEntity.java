package com.grgbanking.counter.iam.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 日志表
 * 
 * @author GrgBanking
 * @email ${email}
 * @date 2021-09-27 11:25:44
 */
@Data
@TableName("sys_log")
public class SysLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private Integer type;
	/**
	 * 
	 */
	private String title;
	/**
	 * 
	 */
	private String serviceId;
	/**
	 * 
	 */
	private String createBy;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 
	 */
	private String remoteAddr;
	/**
	 * 
	 */
	private String userAgent;
	/**
	 * 
	 */
	private String requestUri;
	/**
	 * 
	 */
	private String method;
	/**
	 * 
	 */
	private String params;
	/**
	 * 执行时间
	 */
	private Long time;
	/**
	 * 
	 */
	private Integer enabled;
	/**
	 * 
	 */
	private String exception;

}
