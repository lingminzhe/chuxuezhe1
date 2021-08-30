package com.grgbanking.counter.iam.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 系统菜单表
 *
 */
@Data
@TableName("SYS_MENU")
public class SysMenuEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId
	private Long id;

	private String parentCode;

	private String menuCode;

	private String menuPath;

	private String appType;

	private String type;

	private String name;

	private String i18nCode;

	private String host;

	private String port;

	/**
	 * 请求路径
	 * */
	private String path;

	/**
	 * 图标
	 * */
	private String icon;

	private Integer orderNum;

	private String description;

	/**
	 * 是否启用(0:停用;1:启用)
	 * */
	private String isEnabled;

	private String createdBy;

	private Date creationDate;

	private String lastUpdatedBy;

	private Date lastUpdateDate;

	private String operationId;

	
}
