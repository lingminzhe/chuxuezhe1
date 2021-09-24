package com.grgbanking.counter.device.tencent.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TencentEidToken {

    @ApiModelProperty(value = "e证通token", required = true)
    private String eidToken;

    @ApiModelProperty(value = "人脸核身url", required = false)
    private String url;

}
