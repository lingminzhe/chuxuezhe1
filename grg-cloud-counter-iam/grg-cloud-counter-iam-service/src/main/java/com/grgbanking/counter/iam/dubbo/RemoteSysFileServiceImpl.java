package com.grgbanking.counter.iam.dubbo;

import com.grgbanking.counter.iam.api.dubbo.RemoteSysFileService;
import com.grgbanking.counter.iam.api.entity.SysFileEntity;
import com.grgbanking.counter.iam.service.SysFileService;
import com.grgbanking.counter.oss.api.dto.FileDTO;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class RemoteSysFileServiceImpl implements RemoteSysFileService {
    @Autowired
    private SysFileService sysFileService;

    @Override
    public void save(FileDTO fileDTO) {
        SysFileEntity sysFileEntity = new SysFileEntity();
        BeanUtils.copyProperties(fileDTO,sysFileEntity);
        sysFileService.save(sysFileEntity);
    }
}
