package com.grgbanking.counter.csr.dubbo;


import com.grgbanking.counter.csr.api.dubbo.RemoteFileMgrService;
import com.grgbanking.counter.csr.api.entity.GrgCusFileMgrEntity;
import com.grgbanking.counter.csr.entity.GrgFileManagerEntity;
import com.grgbanking.counter.csr.service.GrgFileManagerService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class RemoteFileMgrServiceImpl implements RemoteFileMgrService {
    @Autowired
    private GrgFileManagerService grgFileManagerService;

    @Override
    public void save(GrgCusFileMgrEntity grgCusFileMgrEntity) {
        GrgFileManagerEntity grgFileManagerEntity = new GrgFileManagerEntity();
        BeanUtils.copyProperties(grgCusFileMgrEntity,grgFileManagerEntity);
        grgFileManagerService.save(grgFileManagerEntity);
    }
}
