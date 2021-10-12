package com.grgbanking.counter.oss.api.dubbo;

import com.grgbanking.counter.csr.api.entity.GrgFileMgrEntity;
import com.grgbanking.counter.oss.api.dto.FileDTO;

public interface RemoteOssService {

    /**
     * 内部文件上传接口
     *
     * @return
     */
    FileDTO upload(byte[] fileByte, String md5, String original, long size, String contentType, GrgFileMgrEntity grgFileMgrEntity, String createUser);

    FileDTO queryFileInfo(String fileName);
}
