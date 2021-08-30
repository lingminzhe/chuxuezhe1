package com.grgbanking.counter.iam.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统区域用户关系表
 *
 * @author cyfeng2
 * @date 2020-12-15
 */
@Data
@TableName("SYS_AREA_USER")
public class SysAreaUserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 客户端id
     */
    @TableId
    private Long id;
    /**
     * 区域ID
     */
    private Long  areaId;
    /**
     *用户ID
     */
    private Long userId;
    /**
     *是否负责人
     */
    private String isLeader;

    /**
     *创建人
     */
    private String createdBy;
    /**
     * 创建时间
     */

    private Date creationDate;

    /**
     *修改人
     */
    private String lastUpdatedBy;

    /**
     *修改时间
     */
    private Date lastUpdateDate;

}
