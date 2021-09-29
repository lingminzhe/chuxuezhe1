package com.grgbanking.counter.csr.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
@TableName("grg_file_manager")
public class GrgFileMgrEntity implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private String id;
    /**
     * 客户ID
     */
    @ApiModelProperty("客户ID")
    private String customerId;
    /**
     * 文件业务类型
     */
    @ApiModelProperty("文件业务类型")
    private String fileBusiType;
    /**
     * 文件ID
     */
    @ApiModelProperty("文件ID")
    private String fileId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}
