package com.grgbanking.counter.iam.entity;

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
 * 字典表
 * 
 * @author GrgBanking
 * @email ${email}
 * @date 2021-09-27
 */
@ApiModel(description = "数据字典")
@Data
@TableName("sys_dict")
public class SysDictEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	@TableId
	@ApiModelProperty(value = "字典id", name = "dictId", required = true, example = "1")
	private Long dictId;
	/**
	 * 类型
	 */
	@ApiModelProperty(value = "字典类型", name = "字典类型", required = true, example = "account_status")
	private String type;
	/**
	 * 描述
	 */
	@ApiModelProperty(value = "字典描述", name = "description", required = true, example = "***")
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
	@ApiModelProperty(value = "字典值", name = "remarks", required = true, example = "已激活、已挂失")
	private String remarks;
	/**
	 * 
	 */
	@ApiModelProperty(value = "", name = "system", required = true, example = "1")
	private Integer system;
	/**
	 * 
	 */
	@ApiModelProperty(value = "是否可用", name = "dictId", required = true, example = "1：可用 0：不可用")
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
