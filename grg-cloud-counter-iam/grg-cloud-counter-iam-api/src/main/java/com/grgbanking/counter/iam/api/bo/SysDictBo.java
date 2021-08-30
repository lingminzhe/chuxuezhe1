package com.grgbanking.counter.iam.api.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 数据字典
 *
 * @author caiyl
 * @email xx@grgbanking.com
 * @date 2021-04-10 12:57:14
 */
@Data
@ApiModel(value = "数据字典数据传输对象")
public class SysDictBo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    private Long id;

    @ApiModelProperty(value = "应用类别")
    private String appType;

    @ApiModelProperty(value = "代码类型")
    private String codeType;

    @ApiModelProperty(value = "代码值")
    private String codeValue;

    @ApiModelProperty(value = "代码值名称")
    private String codeValueName;

    @ApiModelProperty(value = "国际化编码")
    private String i18nCode;

    @ApiModelProperty(value = "是否启用")
    private String isEnabled;

    @ApiModelProperty(value = "顺序号")
    private Integer codeOrder;

    /*@ApiModelProperty(value = "子节点")
    private List<SysDictBo> children = null;*/
}
