package com.grgbanking.counter.iam.dubbo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

    @Override
    public SysFileEntity getFileIdByFileName(String fileName) {
        SysFileEntity one = sysFileService.getOne(new QueryWrapper<SysFileEntity>().eq("file_name", fileName));
        return one;
    }
}
