package com.grgbanking.counter.csr.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@TableName("grg_busi_info")
@Data
public class GrgCusBusiInfoEntity implements Serializable {

    /**
     * 主键
     */
    private String id;
    /**
     * 业务类别
     */
    @ApiModelProperty("业务类别")
    private String busiType;
    /**
     * 业务编码
     */
    @ApiModelProperty("业务编码")
    private String busiNo;
    /**
     * 业务名称
     */
    @ApiModelProperty("业务名称")
    private String busiName;
    /**
     * 业务状态
     */
    @ApiModelProperty("业务状态")
    private String busiStatus;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

}
