package com.grgbanking.counter.csr.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author GrgBanking
 * @email ${email}
 * @date 2021-09-09
 */
@Data
@TableName("grg_busi_info")
public class GrgBusiInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private String id;
	/**
	 * 业务类别
	 */
	private String busiType;
	/**
	 * 业务编码
	 */
	private String busiNo;
	/**
	 * 业务名称
	 */
	private String busiName;
	/**
	 * 业务状态
	 */
	private String busiStatus;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;

}
