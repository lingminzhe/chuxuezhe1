package com.grgbanking.counter.iam.api.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
@Data
public class SysFileDTO implements Serializable {
    /**
     * 编号
     */
    private Long id;

    /**
     * 文件名
     */
    @NotBlank(message = "文件名")
    private String fileName;

    /**
     * 分组
     */
    private String bucketName;

    /**
     * 文件原名
     */
    private String originalName;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 文件md5值
     */
    private String fileMd5;

    /**
     * 文件大小
     */
    private String fileSize;

    /**
     * 上传者id
     */
    private String createUser;

    /**
     * 上传时间
     */
    private String createTime;

    /**
     * 修改者id
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private Long updateTime;

    /**
     *
     */
    private String enabled;
}
