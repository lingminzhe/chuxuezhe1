package com.grgbanking.counter.iam.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 部门关系表
 * 
 * @author GrgBanking
 * @email ${email}
 * @date 2021-09-27 11:25:44
 */
@Data
@TableName("sys_dept_relation")
public class SysDeptRelationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 祖先节点
	 */
	@TableId
	private Long ancestor;
	/**
	 * 后代节点
	 */
	private Long descendant;

}
