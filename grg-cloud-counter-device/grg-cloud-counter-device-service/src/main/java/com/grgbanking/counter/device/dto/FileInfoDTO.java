package com.grgbanking.counter.device.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: Ye Kaitao
 * @create: 2021-10-16
 */
@Data
public class FileInfoDTO {

    @ApiModelProperty(value = "�ļ�,Base64��ʽ",required = true)
    private String file;
    /**
     *
     */
    @ApiModelProperty(value = "�ļ�ҵ������,���֤���棺101�����棺102��ǩ����103",required = true,example = "101")
    private String fileBusiType;


}
