package com.grgbanking.counter.oss.dubbo;

import com.grgbanking.counter.csr.api.entity.GrgFileMgrEntity;
import com.grgbanking.counter.oss.api.dto.FileDTO;
import com.grgbanking.counter.oss.api.dubbo.RemoteOssService;
import com.grgbanking.counter.oss.service.OssService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class RemoteOssServiceImpl implements RemoteOssService {

    @Autowired
    private OssService ossService;

    @Override
    public FileDTO upload(byte[] fileByte, String md5, String original, long size, String contentType, GrgFileMgrEntity grgFileMgrEntity, String createUser) {
        return ossService.upload(fileByte, md5, original, size, contentType, grgFileMgrEntity,createUser);
    }

    @Override
    public FileDTO queryFileInfo(String fileName) {
        return ossService.queryFileInfo(fileName);
    }
}
