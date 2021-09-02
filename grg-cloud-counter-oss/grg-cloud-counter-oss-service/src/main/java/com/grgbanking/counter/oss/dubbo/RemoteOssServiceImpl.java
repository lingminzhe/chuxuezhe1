package com.grgbanking.counter.oss.dubbo;

import com.grgbanking.counter.oss.api.dubbo.RemoteOssService;
import com.grgbanking.counter.oss.service.OssService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@DubboService
public class RemoteOssServiceImpl implements RemoteOssService {

    @Autowired
    private OssService ossService;

    @Override
    public Map<String, Object> upload(byte[] fileByte, String md5, String original, long size, String contentType) {
        return ossService.upload(fileByte, md5, original, size, contentType);
    }
}
