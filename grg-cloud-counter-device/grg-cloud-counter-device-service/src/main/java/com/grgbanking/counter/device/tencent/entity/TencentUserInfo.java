package com.grgbanking.counter.device.tencent.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TencentUserInfo {

    @ApiModelProperty(name = "userId", value = "用户id", required = false)
    private String userId;

    @ApiModelProperty(name = "name", value = "姓名，当不启用ocr时需传入", required = false)
    private String name;

    @ApiModelProperty(name = "idCard", value = "证件号，当不启用ocr时需传入", required = false)
    private String idCard;

    @ApiModelProperty(name = "type", value = "1：传身份证正反面OCR\r" +
            "2：传身份证正面OCR\r" +
            "3：用户手动输入\n" +
            "4：客户后台传入，默认1 ", required = false)
    private String type = "1";

    @ApiModelProperty(name = "redirectUrl", value = "跳转路径", required = false)
    private String redirectUrl;

}
