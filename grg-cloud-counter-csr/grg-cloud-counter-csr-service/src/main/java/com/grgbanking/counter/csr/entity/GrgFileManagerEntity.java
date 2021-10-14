package com.grgbanking.counter.csr.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 附件管理表
 * 
 * @author GrgBanking
 * @email ${email}
 * @date 2021-09-09
 */
@Data
@TableName("grg_file_manager")
public class GrgFileManagerEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键ID")
	@TableId
	private String id;
	/**
	 * 客户ID
	 */
	@ApiModelProperty(value = "客户ID")
	private String customerId;
	/**
	 * 文件类型
	 */
	@ApiModelProperty(value = "文件类型")
	private String fileBusiType;
	/**
	 * 文件ID
	 */
	@ApiModelProperty(value = "文件ID")
	private String fileId;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(hidden = true)
	private Date createTime;
	/**
	 * 更新时间
	 */
	@ApiModelProperty(hidden = true)
	private Date updateTime;
	/**
	 * 会话ID
	 */
	@ApiModelProperty(value = "会话ID")
	private String sessionId;

}
