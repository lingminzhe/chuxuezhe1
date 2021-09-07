package com.grgbanking.counter.bank.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户详情表
 * 
 * @author GRGBanking
 * @email ${email}
 * @date 2021-09-03 13:43:56
 */
@Data
@TableName("grg_customer")
public class GrgCustomerEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private String id;
	/**
	 * 性别
	 */
	private String gender;
	/**
	 * 出生日期
	 */
	private String cusbirthday;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * 联系人来源
	 */
	private String csource;
	/**
	 * 语言
	 */
	private String language;
	/**
	 * 婚姻状况
	 */
	private String marriage;
	/**
	 * 学历
	 */
	private String education;
	/**
	 * 证件类型
	 */
	private String identifytype;
	/**
	 * 证件号码
	 */
	private String identifynumber;
	/**
	 * 证件到期日
	 */
	private String idcardexpiredate;
	/**
	 * 职业
	 */
	private String occupation;
	/**
	 * 电子邮件
	 */
	private String email;
	/**
	 * 备用电子邮件
	 */
	private String emailalt;
	/**
	 * 手机号码
	 */
	private String mobileno;
	/**
	 * 备用手机号码
	 */
	private String mobilealt;
	/**
	 * 办公电话
	 */
	private String phone;
	/**
	 * 办公分机
	 */
	private String extension;
	/**
	 * 住宅电话
	 */
	private String familyphone;
	/**
	 * 传真号码
	 */
	private String fax;
	/**
	 * 国家
	 */
	private String country;
	/**
	 * 省
	 */
	private String province;
	/**
	 * 市(区)县
	 */
	private String city;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 邮政编码
	 */
	private String postcode;
	/**
	 * 微信号
	 */
	private String weixin;
	/**
	 * 微信昵称
	 */
	private String weixinname;
	/**
	 * 微信ID
	 */
	private String weixinid;
	/**
	 * 最后联系时间
	 */
	private Date touchtime;
	/**
	 * 数据状态
	 */
	private Integer datastatus;
	/**
	 * 创建者
	 */
	private String createBy;
	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	private Date createDate;
	/**
	 * 更新者
	 */
	private String updateBy;
	/**
	 * 更新时间
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateDate;
	/**
	 * 备注信息
	 */
	private String remarks;
	/**
	 * 删除标记
	 */
	private String delFlag;

}
