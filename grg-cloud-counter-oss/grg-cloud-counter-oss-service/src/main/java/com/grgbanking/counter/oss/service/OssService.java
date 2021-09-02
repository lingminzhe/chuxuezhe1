package com.grgbanking.counter.oss.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * aws-s3 通用存储操作 支持所有兼容s3协议的云存储: {阿里云OSS，腾讯云COS，七牛云，京东云，minio 等}
 *
 */
public interface OssService {

    /**
     * 上传文件
     * @return
     */
    Map<String, Object> upload(byte[] fileByte,String md5, String original, long size, String contentType);

    /**
     * 查询列表
     * @param fileType
     * @param userId
     */
    // TODO 完善返回值对象
    Map<String, Object> list(String fileType, String userId);

    /**
     * 查询单个文件信息
     * @param fileName
     */
    // TODO 完善返回值对象
    Map<String,Object> queryFileInfo(String fileName);


    /**
     * 删除文件
     * @param fileName
     * @return
     */
    boolean deleteFile(String fileName);

}
