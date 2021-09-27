package com.grgbanking.counter.iam.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 字典表
 * 
 * @author GrgBanking
 * @email ${email}
 * @date 2021-09-27
 */
@Data
@TableName("sys_dict")
public class SysDictEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	@TableId
	private Long dictId;
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 描述
	 */
	private String description;
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
	private String remarks;
	/**
	 * 
	 */
	private Integer system;
	/**
	 * 
	 */
	private Integer enabled;

	/**
	 *
	 */
	@TableField(fill = FieldFill.INSERT)
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
