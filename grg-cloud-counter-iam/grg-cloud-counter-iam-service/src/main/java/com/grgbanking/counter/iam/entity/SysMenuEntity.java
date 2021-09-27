package com.grgbanking.counter.iam.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 菜单权限表
 * 
 * @author GrgBanking
 * @email ${email}
 * @date 2021-09-27 11:25:44
 */
@Data
@TableName("sys_menu")
public class SysMenuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 菜单ID
	 */
	@TableId
	private Long menuId;
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private String permission;
	/**
	 * 
	 */
	private String path;
	/**
	 * 父菜单ID
	 */
	private Long parentId;
	/**
	 * 
	 */
	private String icon;
	/**
	 * 排序值
	 */
	private Integer sort;
	/**
	 * 
	 */
	private Integer keepAlive;
	/**
	 * 
	 */
	private Integer type;
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
	private Integer enabled;

}
