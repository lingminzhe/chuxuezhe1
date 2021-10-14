package com.grgbanking.counter.csr.dubbo;


import com.grgbanking.counter.csr.api.dubbo.RemoteFileMgrService;
import com.grgbanking.counter.csr.api.entity.GrgFileMgrEntity;
import com.grgbanking.counter.csr.entity.GrgFileManagerEntity;
import com.grgbanking.counter.csr.service.GrgFileManagerService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class RemoteFileMgrServiceImpl implements RemoteFileMgrService {

    @Autowired
    private GrgFileManagerService grgFileManagerService;



    @Override
    public void save(GrgFileMgrEntity grgFileMgrEntity) {
        GrgFileManagerEntity grgFileManagerEntity = new GrgFileManagerEntity();
        BeanUtils.copyProperties(grgFileMgrEntity,grgFileManagerEntity);
        grgFileManagerService.save(grgFileManagerEntity);
    }

    @Override
    public GrgFileMgrEntity getByFileName(String fileName) {
//        FileDTO fileDTO = remoteOssService.queryFileInfo(fileName);
//        System.out.println(fileDTO);
        return null;
    }

    @Override
    public List<String> getFileIdBySessionId(String fileId) {
        return null;
    }
}
