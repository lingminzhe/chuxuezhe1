package com.grgbanking.counter.bank.api.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class GrgCusInfoEntity implements Serializable {

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private String id;
    /**
     * 性别
     */
    @ApiModelProperty("性别")
    private String gender;
    /**
     * 出生日期
     */
    @ApiModelProperty("出生日期")
    private String cusbirthday;
    /**
     * 姓名
     */
    @ApiModelProperty("姓名")
    private String name;
    /**
     * 昵称
     */
    @ApiModelProperty("昵称")
    private String nickname;
    /**
     * 联系人来源
     */
    @ApiModelProperty("联系人来源")
    private String csource;
    /**
     * 语言
     */
    @ApiModelProperty("语言")
    private String language;
    /**
     * 婚姻状况
     */
    @ApiModelProperty("婚姻状况")
    private String marriage;
    /**
     * 学历
     */
    @ApiModelProperty("学历")
    private String education;
    /**
     * 证件类型
     */
    @ApiModelProperty("证件类型")
    private String identifytype;
    /**
     * 证件号码
     */
    @ApiModelProperty("证件号码")
    private String identifynumber;
    /**
     * 证件到期日
     */
    @ApiModelProperty("证件到期日")
    private String idcardexpiredate;
    /**
     * 职业
     */
    @ApiModelProperty("职业")
    private String occupation;
    /**
     * 电子邮件
     */
    @ApiModelProperty("电子邮件")
    private String email;
    /**
     * 备用电子邮件
     */
    @ApiModelProperty("备用电子邮件")
    private String emailalt;
    /**
     * 手机号码
     */
    @ApiModelProperty("手机号码")
    private String mobileno;
    /**
     * 备用手机号码
     */
    @ApiModelProperty("备用手机号码")
    private String mobilealt;
    /**
     * 办公电话
     */
    @ApiModelProperty("办公电话")
    private String phone;
    /**
     * 办公分机
     */
    @ApiModelProperty("办公分机")
    private String extension;
    /**
     * 住宅电话
     */
    @ApiModelProperty("住宅电话")
    private String familyphone;
    /**
     * 传真号码
     */
    @ApiModelProperty("传真号码")
    private String fax;
    /**
     * 国家
     */
    @ApiModelProperty("国家")
    private String country;
    /**
     * 省
     */
    @ApiModelProperty("省")
    private String province;
    /**
     * 市(区)县
     */
    @ApiModelProperty("市(区)县")
    private String city;
    /**
     * 地址
     */
    @ApiModelProperty("地址")
    private String address;
    /**
     * 邮政编码
     */
    @ApiModelProperty("邮政编码")
    private String postcode;
    /**
     * 微信号
     */
    @ApiModelProperty("微信号")
    private String weixin;
    /**
     * 微信昵称
     */
    @ApiModelProperty("微信昵称")
    private String weixinname;
    /**
     * 微信ID
     */
    @ApiModelProperty("微信ID")
    private String weixinid;
    /**
     * 最后联系时间
     */
    @ApiModelProperty("最后联系时间")
    private Date touchtime;
    /**
     * 数据状态
     */
    @ApiModelProperty("数据状态")
    private Integer datastatus;
    /**
     * 创建者
     */
    @ApiModelProperty("创建者")
    private String createBy;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createDate;
    /**
     * 更新者
     */
    @ApiModelProperty("更新者")
    private String updateBy;
    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private Date updateDate;
    /**
     * 备注信息
     */
    @ApiModelProperty("备注信息")
    private String remarks;
    /**
     * 删除标记
     */
    @ApiModelProperty("删除标记")
    private String delFlag;
    /**
     * 头像
     */
    @ApiModelProperty("头像")
    private String portrait;
}
