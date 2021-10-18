package com.grgbanking.counter.csr.api.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class UploadFileDTO implements Serializable {

    private static final long serialVersionUID = -3275256067334372054L;

    private String originalName;

    private String fileName;

    private String fileMd5;

    private long fileSize;

    private String fileType;

    private String url;

    private String userId;

    private String bucketName;

    private String enable;

    private String createUser;

    private LocalDateTime createTime;

    private String updateUser;

    private LocalDateTime updateTime;

    private Integer enabled;

    private String fileBusiType;

    private String fileId;
}
