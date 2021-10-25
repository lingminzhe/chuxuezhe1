package com.grgbanking.counter.device.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author: Ye Kaitao
 * @create: 2021-10-18
 */
@Data
public class UploadFileDTO {
    @ApiModelProperty(value = "нд╪Ч",required = true)
    private List<FileInfoDTO> fileDTO;

    @ApiModelProperty(required = true)
    private String userId;
}
