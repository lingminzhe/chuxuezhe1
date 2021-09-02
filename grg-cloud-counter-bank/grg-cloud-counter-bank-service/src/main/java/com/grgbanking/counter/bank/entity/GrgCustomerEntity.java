package com.grgbanking.counter.bank.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 客户详情表
 * </p>
 *
 * @author <a href="https://grgbanking.com">grgbanking</a>
 * @since 2021-09-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("grg_customer")
public class GrgCustomerEntity extends Model<GrgCustomerEntity> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 性别
     */
    @TableField("gender")
    private String gender;

    /**
     * 出生日期
     */
    @TableField("cusbirthday")
    private String cusbirthday;

    /**
     * 姓名
     */
    @TableField("name")
    private String name;

    /**
     * 昵称
     */
    @TableField("nickname")
    private String nickname;

    /**
     * 联系人来源
     */
    @TableField("csource")
    private String csource;

    /**
     * 语言
     */
    @TableField("language")
    private String language;

    /**
     * 婚姻状况
     */
    @TableField("marriage")
    private String marriage;

    /**
     * 学历
     */
    @TableField("education")
    private String education;

    /**
     * 证件类型
     */
    @TableField("identifytype")
    private String identifytype;

    /**
     * 证件号码
     */
    @TableField("identifynumber")
    private String identifynumber;

    /**
     * 证件到期日
     */
    @TableField("idcardexpiredate")
    private String idcardexpiredate;

    /**
     * 职业
     */
    @TableField("occupation")
    private String occupation;

    /**
     * 电子邮件
     */
    @TableField("email")
    private String email;

    /**
     * 备用电子邮件
     */
    @TableField("emailalt")
    private String emailalt;

    /**
     * 手机号码
     */
    @TableField("mobileno")
    private String mobileno;

    /**
     * 备用手机号码
     */
    @TableField("mobilealt")
    private String mobilealt;

    /**
     * 办公电话
     */
    @TableField("phone")
    private String phone;

    /**
     * 办公分机
     */
    @TableField("extension")
    private String extension;

    /**
     * 住宅电话
     */
    @TableField("familyphone")
    private String familyphone;

    /**
     * 传真号码
     */
    @TableField("fax")
    private String fax;

    /**
     * 国家
     */
    @TableField("country")
    private String country;

    /**
     * 省
     */
    @TableField("province")
    private String province;

    /**
     * 市(区)县
     */
    @TableField("city")
    private String city;

    /**
     * 地址
     */
    @TableField("address")
    private String address;

    /**
     * 邮政编码
     */
    @TableField("postcode")
    private String postcode;

    /**
     * 微信号
     */
    @TableField("weixin")
    private String weixin;

    /**
     * 微信昵称
     */
    @TableField("weixinname")
    private String weixinname;

    /**
     * 微信ID
     */
    @TableField("weixinid")
    private String weixinid;

    /**
     * 最后联系时间
     */
    @TableField("touchtime")
    private LocalDateTime touchtime;

    /**
     * 数据状态
     */
    @TableField("datastatus")
    private Integer datastatus;

    /**
     * 创建者
     */
    @TableField("create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @TableField("create_date")
    private LocalDateTime createDate;

    /**
     * 更新者
     */
    @TableField("update_by")
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField("update_date")
    private LocalDateTime updateDate;

    /**
     * 备注信息
     */
    @TableField("remarks")
    private String remarks;

    /**
     * 删除标记
     */
    @TableField("del_flag")
    private String delFlag;

}
