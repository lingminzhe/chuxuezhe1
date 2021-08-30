package com.grgbanking.counter.iam.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 系统用户表
 *
 * @author lggui1
 * @email lggui1@grgbanking.com
 * @date 2021-01-12 13:58:41
 */
@Data
@TableName("sys_user")
public class SysUserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;
    /**
     * 账号
     **/
    private String username;
    /**
     * 姓名
     **/
    private String name;
    /**
     * 用户类型
     **/

    private String type;
    /**
     * 密码
     **/
    private String password;
    /**
     * 描述
     **/
    private String description;
    /**
     * 密码最后一次修改时间
     **/
    private Date passwordChangedDate;
    /**
     * 账号是否启用 0停用，1启用
     **/
    private String isEnabled;
    /**
     * 账号是否锁定
     **/
    private String isLocked;
    /**
     * 账号锁定原因
     **/
    private String lockedReason;
    /**
     * 帐号锁定时间
     */
    private Date lockedTime;
    /**
     * 性别
     **/
    private String sex;
    /**
     * 生日
     **/
    private Date birthdate;
    /**
     * 国籍
     */
    private String nationality;
    /**
     * 证件类型
     */
    private String credentialsType;
    /**
     * 证件号码
     */
    private String credentialsNumber;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 移动电话
     */
    private String mobileTelephone;
    /**
     * 家庭电话
     */
    private String homeTelephone;
    /**
     * 办公电话
     */
    private String officeTelephone;
    /**
     * 传真
     */
    private String fax;
    /**
     * 家庭住址
     */
    private String homeAddress;
    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private Date creationDate;
    /**
     * 最后修改人
     */
    private String lastUpdatedBy;
    /**
     * 最后修改时间
     */
    private Date lastUpdateDate;

    /**
     * 操作id
     */
    private String operationId;

}
