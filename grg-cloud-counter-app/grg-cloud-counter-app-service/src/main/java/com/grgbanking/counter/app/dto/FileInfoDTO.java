package com.grgbanking.counter.app.dto;

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

    @ApiModelProperty(value = "文件格式,区分是否是svg  若为svg 则传svg，其他不传",required = true,example = "svg")
    private String fileType;


}
