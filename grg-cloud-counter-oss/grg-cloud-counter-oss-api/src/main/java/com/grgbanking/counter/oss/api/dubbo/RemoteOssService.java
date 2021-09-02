package com.grgbanking.counter.oss.api.dubbo;

import java.util.Map;

public interface RemoteOssService {

    /**
     * 内部文件上传接口
     *
     * @return
     */
    Map<String, Object> upload(byte[] fileByte, String md5, String original, long size, String contentType);

}
