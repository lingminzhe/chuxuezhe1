package com.grgbanking.counter.iam.api.dubbo;

import com.grgbanking.counter.oss.api.dto.FileDTO;

public interface RemoteSysFileService {
    /**
     * sys_file文件上传存表
     */
    void save(FileDTO fileDTO);
}
