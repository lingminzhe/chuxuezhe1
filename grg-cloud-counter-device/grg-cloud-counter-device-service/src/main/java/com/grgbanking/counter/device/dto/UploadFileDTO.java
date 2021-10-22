package com.grgbanking.counter.device.dto;

import lombok.Data;

import java.util.List;

/**
 * @author: Ye Kaitao
 * @create: 2021-10-18
 */
@Data
public class UploadFileDTO {
    private List<FileInfoDTO> fileDTO;
    private String userId;
}
