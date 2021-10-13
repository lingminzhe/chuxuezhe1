package com.grgbanking.counter.csr.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 业务操作流水表
 * 
 * @author GrgBanking
 * @email ${email}
 * @date 2021-09-09
 */
@ApiModel
@Data
@TableName("grg_busi_opt")
public class GrgBusiOptEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(type = IdType.ASSIGN_UUID)
	private String id;
	/**
	 * 会话id
	 */
	private String sessionId;
	/**
	 * 业务编码
	 */
	@ApiModelProperty(name = "busiNo", value = "业务编号", required = true)
	private String busiNo;
	/**
	 * 坐席ID
	 */
	@ApiModelProperty(name = "userId", value = "坐席id", required = true)
	private String userId;
	/**
	 * 客户ID
	 */
	private String customerId;
	/**
	 * 业务办理状态
	 * (1、已完成  2、正在进行 3、未完成)
	 */
	private String busiOptStatus;
	/**
	 * 业务操作流水号
	 */
	private String busiOptNo;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;

}
