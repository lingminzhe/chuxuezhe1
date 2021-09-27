package com.grgbanking.counter.iam.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 文件管理表
 * 
 * @author GrgBanking
 * @email ${email}
 * @date 2021-09-27 11:25:44
 */
@Data
@TableName("sys_file")
public class SysFileEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	@TableId
	private Long id;
	/**
	 * 文件名
	 */
	private String fileName;
	/**
	 * 
	 */
	private String bucketName;
	/**
	 * 文件原名
	 */
	private String originalName;
	/**
	 * 文件类型
	 */
	private String type;
	/**
	 * 文件md5值
	 */
	private String fileMd5;
	/**
	 * 文件大小
	 */
	private Long fileSize;
	/**
	 * 上传者id
	 */
	private Long createUser;
	/**
	 * 上传时间
	 */
	private Date createTime;
	/**
	 * 
	 */
	private String updateUser;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 
	 */
	private Integer enabled;

}
