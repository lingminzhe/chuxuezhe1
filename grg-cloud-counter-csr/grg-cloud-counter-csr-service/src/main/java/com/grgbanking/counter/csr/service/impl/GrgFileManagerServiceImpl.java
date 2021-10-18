package com.grgbanking.counter.csr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grgbanking.counter.common.core.util.PageUtils;
import com.grgbanking.counter.common.core.util.Query;
import com.grgbanking.counter.csr.api.dto.UploadFileDTO;
import com.grgbanking.counter.csr.api.dubbo.RemoteFileMgrService;
import com.grgbanking.counter.csr.dao.GrgFileManagerDao;
import com.grgbanking.counter.csr.entity.GrgFileManagerEntity;
import com.grgbanking.counter.csr.service.GrgFileManagerService;
import com.grgbanking.counter.oss.api.dto.FileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service("grgFileManagerService")
public class GrgFileManagerServiceImpl extends ServiceImpl<GrgFileManagerDao, GrgFileManagerEntity> implements GrgFileManagerService, RemoteFileMgrService {

    @Autowired
    private GrgFileManagerDao fileManagerDao;

    /**
     * 获取fileId
     * @param fileId
     * @return
     */
    @Override
    public List<String> getFileIdBySessionId(String fileId) {
        return null;
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GrgFileManagerEntity> page = this.page(
                new Query<GrgFileManagerEntity>().getPage(params),
                new QueryWrapper<GrgFileManagerEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<FileDTO> getByCustomerId(String customerId) {

        List<GrgFileManagerEntity> entities = this.baseMapper.selectList(new QueryWrapper<GrgFileManagerEntity>().eq("customer_id", customerId));
        return getList(entities);
    }

    @Override
    public List<FileDTO> getBySessionId(String sessionId) {
        List<GrgFileManagerEntity> entities = this.baseMapper.selectList(new QueryWrapper<GrgFileManagerEntity>().eq("session_id", sessionId));

        return getList(entities);
    }

    @Override
    public List<FileDTO> getBySessionIdAndType(String sessionId, List<String> busiType) {
//        List<GrgFileManagerEntity> entities = this.baseMapper.selectBatchIds(new QueryWrapper<GrgFileManagerEntity>()
//                .eq("session_id", sessionId).eq("file_Busi_Type",busiType));
        List<GrgFileManagerEntity> entities = fileManagerDao.getBySessionIdAndType(sessionId,busiType);

        return getList(entities);
//        return getList(entities);
    }

    private List<FileDTO> getList(List<GrgFileManagerEntity> entities) {
        List<FileDTO> list = new ArrayList<>();
        for (GrgFileManagerEntity entity:entities) {
            FileDTO fileDTO = new FileDTO();
            fileDTO.setFileId(entity.getFileId());
            fileDTO.setFileBusiType(entity.getFileBusiType());
            list.add(fileDTO);
        }

        return list;
    }

}