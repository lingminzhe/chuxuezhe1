package com.grgbanking.counter.csr.api.dubbo;

import com.grgbanking.counter.csr.api.entity.GrgFileMgrEntity;

public interface RemoteFileMgrService {
    /**
     * grg_file_manager文件上传存表
     */
     void save(GrgFileMgrEntity grgFileMgrEntity);
}
