package com.grgbanking.counter.iam.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户与机构对应关系表
 *
 * @author lggui1
 * @date 2021年1月7日
 */
@Data
@TableName("sys_org_user")
public class SysOrgUserEntity implements Serializable {

    private static final long serialVersionUID = -2039898896361938824L;

    @TableId
    private Long id;

    /***组织id*/
    private Long orgId;

    /***用户id*/
    private Long userId;

    /***是否负责人*/
    private String isLeader;

    /***创建人*/
    private String createdBy;

    private Date creationDate;

    private String lastUpdatedBy;

    private Date lastUpdateDate;

    private String operationId;

}
