package com.grgbanking.counter.iam.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 部门关系表
 * </p>
 *
 * @author <a href="https://grgbanking.com">grgbanking</a>
 * @since 2021-08-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_dept_relation")
public class SysDeptRelationEntity extends Model<SysDeptRelationEntity> {

    private static final long serialVersionUID = 1L;

    /**
     * 祖先节点
     */
    @TableId(value = "ancestor", type = IdType.ASSIGN_ID)
    private Long ancestor;

    /**
     * 后代节点
     */
    @TableField("descendant")
    private Long descendant;


}
