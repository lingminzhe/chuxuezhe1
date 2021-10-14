package com.grgbanking.counter.iam.api.dubbo;

import com.grgbanking.counter.iam.api.entity.SysFileEntity;
import com.grgbanking.counter.oss.api.dto.FileDTO;

public interface RemoteSysFileService {
    /**
     * sys_file文件上传存表
     */
    void save(FileDTO fileDTO);

    /**
     * 根据上传的文件名查到生成的id
     * @return
     */
    SysFileEntity getFileIdByFileName(String fileName);

    SysFileEntity getFileByFileId(String fileId);
}
