package com.grgbanking.counter.device.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: Ye Kaitao
 * @create: 2021-10-16
 */
@Data
public class FileInfoDTO {

    @ApiModelProperty(value = "文件,Base64格式",required = true)
    private String file;
    /**
     *
     */
    @ApiModelProperty(value = "文件业务类型,身份证正面：101，反面：102，签名：103",required = true,example = "101")
    private String fileBusiType;


}
