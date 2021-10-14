package com.grgbanking.counter.oss.service;

import com.grgbanking.counter.csr.api.entity.GrgFileMgrEntity;
import com.grgbanking.counter.oss.api.dto.FileDTO;

import java.util.List;

/**
 * aws-s3 通用存储操作 支持所有兼容s3协议的云存储: {阿里云OSS，腾讯云COS，七牛云，京东云，minio 等}
 */
public interface OssService {

    /**
     * 上传文件
     *
     * @return
     */
    FileDTO upload(byte[] fileByte, String md5, String original, long size, String contentType, GrgFileMgrEntity grgFileMgrEntity, String createUser);

    /**
     * 查询列表
     *
     * @param fileType
     * @param userId
     */
    FileDTO list(String fileType, String userId);

    /**
     * 查询单个文件信息
     *
     * @param fileName
     */
    FileDTO queryFileInfo(String fileName);


    /**
     * 删除文件
     *
     * @param fileName
     * @return
     */
    boolean deleteFile(String fileName);

    /**
     * 根据id查找文件
     * @param fileId
     * @return
     */
    List<FileDTO> listFileInfo(String fileId);
}
