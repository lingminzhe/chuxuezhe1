package com.grgbanking.counter.csr.api.dubbo;

import com.grgbanking.counter.csr.api.entity.GrgFileMgrEntity;

import java.util.List;

public interface RemoteFileMgrService {
    /**
     * grg_file_manager文件上传存表
     */
     default void save(GrgFileMgrEntity grgFileMgrEntity){

     };

    default GrgFileMgrEntity getByFileName(String fileName){
        return null;
    };

    default List<String> getFileIdBySessionId(String fileId){
        return null;
    };

    default List<String> getFileIdByCustomerId(String customerId){
        return null;
    };
}
