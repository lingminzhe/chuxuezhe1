package com.grgbanking.counter.iam.api.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 系统用户表
 *
 * @author lggui1
 * @email lggui1@grgbanking.com
 * @date 2021-01-12 13:58:41
 */
@Data
public class SysUserQueryVo implements Serializable {

    private static final long serialVersionUID = -7686541104174907592L;

    @ApiModelProperty(value = "ID")
    private Long id;
    /**
     * 账号
     **/
    @ApiModelProperty(value = "账号")
    private String username;
    /**
     * 姓名
     **/
    @ApiModelProperty(value = "姓名")
    private String name;
    /**
     * 用户类型
     **/
    @ApiModelProperty(value = "用户类型")
    private String type;
    /**
     * 描述
     **/
    @ApiModelProperty(value = "描述")
    private String description;
    /**
     * 密码最后一次修改时间
     **/
    @ApiModelProperty(value = "密码最后一次修改时间")
    private Date passwordChangedDate;
    /**
     * 账号是否启用 0停用，1启用
     **/
    @ApiModelProperty(value = "账号是否启用")
    private String isEnabled;
    /**
     * 账号是否锁定
     **/
    @ApiModelProperty(value = "账号是否锁定")
    private String isLocked;
    /**
     * 账号锁定理由
     **/
    @ApiModelProperty(value = "账号锁定理由")
    private String lockedReason;
    /**
     * 性别
     **/
    @ApiModelProperty(value = "性别")
    private String sex;
    /**
     * 生日
     **/
    @ApiModelProperty(value = "生日")
    private Date birthdate;
    /**
     * 国籍
     */
    @ApiModelProperty(value = "国籍")
    private String nationality;
    /**
     * 证件类型
     */
    @ApiModelProperty(value = "证件类型")
    private String credentialsType;
    /**
     * 证件号码
     */
    @ApiModelProperty(value = "证件号码")
    private String credentialsNumber;
    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;
    /**
     * 移动电话
     */
    @ApiModelProperty(value = "移动电话")
    private String mobileTelephone;
    /**
     * 家庭电话
     */
    @ApiModelProperty(value = "家庭电话")
    private String homeTelephone;
    /**
     * 办公电话
     */
    @ApiModelProperty(value = "办公电话")
    private String officeTelephone;
    /**
     * 传真
     */
    @ApiModelProperty(value = "传真")
    private String fax;
    /**
     * 家庭住址
     */
    @ApiModelProperty(value = "家庭住址")
    private String homeAddress;
    /**
     * 帐号锁定时间
     */
    @ApiModelProperty(value = "账号锁定时间")
    private Date lockedTime;

    @ApiModelProperty(value = "所属机构id")
    private Long joinOrgId;

    @ApiModelProperty(value = "所属机构名称")
    private String joinOrgName;

    @ApiModelProperty(value = "所属机构i18nCode")
    private String joinOrgI18nCode;

    @ApiModelProperty(value = "管理机构idList")
    private List<Long> manageOrgIdList;

    @ApiModelProperty(value = "管理区域idList")
    private List<Long> areaIdList;

    @ApiModelProperty(value = "角色列表idList")
    private List<Long> roleIdList;

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
