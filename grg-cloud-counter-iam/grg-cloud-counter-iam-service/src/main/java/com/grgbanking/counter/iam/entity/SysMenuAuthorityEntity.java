package com.grgbanking.counter.iam.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
@TableName("SYS_MENU_AUTHORITY")
public class SysMenuAuthorityEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 权限ID
     */
    private Long authorityId;

    /**
     * 菜单ID
     */
    private Long menuId;
    /**
     * 是否启用
    * */
    private String isEnabled;

    /**
     *创建人
     * */
    private String createdBy;
    /**
     *创建时间
     * */
    private Date creationDate;
    /**
     *修改人
     * */
    private String lastUpdatedBy;
    /**
     *修改时间
     * */
    private Date lastUpdateDate;
    /**
     *操作序号
     * */
    private String operationId;


}
