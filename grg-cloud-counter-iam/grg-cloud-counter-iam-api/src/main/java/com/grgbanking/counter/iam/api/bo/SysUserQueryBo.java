package com.grgbanking.counter.iam.api.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author lggui1
 * @Date 2021/1/14
 * @Description
 **/
@Data
@ApiModel(value = "用户列表查询信息")
public class SysUserQueryBo implements Serializable {

    private static final long serialVersionUID = -2421255732605298067L;

    @ApiModelProperty(value = "用户账号")
    private String username;

    @ApiModelProperty(value = "机构编码")
    private String orgCode;

    @ApiModelProperty(value = "机构结构")
    private String orgPath;

    @ApiModelProperty(value = "启用状态")
    private String isEnabled;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "锁定状态")
    private String isLocked;
}
