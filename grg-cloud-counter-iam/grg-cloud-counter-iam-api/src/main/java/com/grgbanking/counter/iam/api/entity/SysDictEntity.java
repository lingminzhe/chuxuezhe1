package com.grgbanking.counter.iam.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 字典表
 * </p>
 *
 * @author <a href="https://grgbanking.com">grgbanking</a>
 * @since 2021-08-31
 */
@ApiModel(description = "数据字典")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_dict")
public class SysDictEntity extends Model<SysDictEntity> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @ApiModelProperty(value = "字典id", name = "dictId", required = true, example = "1")
    @TableId(value = "dict_id", type = IdType.AUTO)
    private Long dictId;

    @ApiModelProperty(value = "字典类型", name = "字典类型", required = true, example = "account_status")
    @TableField("type")
    private String type;

    @ApiModelProperty(value = "字典描述", name = "description", required = true, example = "***")
    @TableField("description")
    private String description;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "字典值", name = "remarks", required = true, example = "已激活、已挂失")
    @TableField("remarks")
    private String remarks;

    @ApiModelProperty(value = " ", name = "system", required = true, example = "1")
    @TableField("system")
    private Integer system;

    @TableLogic
    @TableField("enabled")
    private Integer enabled;

}
