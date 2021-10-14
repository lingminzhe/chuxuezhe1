package com.grgbanking.counter.oss.dubbo;

import com.grgbanking.counter.csr.api.dubbo.RemoteFileMgrService;
import com.grgbanking.counter.csr.api.entity.GrgFileMgrEntity;
import com.grgbanking.counter.oss.api.dto.FileDTO;
import com.grgbanking.counter.oss.api.dubbo.RemoteOssService;
import com.grgbanking.counter.oss.service.OssService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@DubboService
public class RemoteOssServiceImpl implements RemoteOssService {

    @Autowired
    private OssService ossService;

    @DubboReference
    private RemoteFileMgrService fileMgrService;

    @Override
    public FileDTO upload(byte[] fileByte, String md5, String original, long size, String contentType, @Valid GrgFileMgrEntity grgFileMgrEntity, String createUser) {
        return ossService.upload(fileByte, md5, original, size, contentType,  grgFileMgrEntity,createUser);
    }

    /**
     * 根据customerId 查附件记录
     * @param customerId
     * @return
     */
    @Override
    public List<FileDTO> queryFileInfoByCustomerId(String customerId) {
        //1、根据customerId查出对应的fileId
        List<String > listFileId = fileMgrService.getFileIdByCustomerId(customerId);
        //用于保存FileDTO
        List<FileDTO> list = new ArrayList<>();
        // 2、根据fileId查出sys_file中的filename
        for (String fileName:listFileId) {
            FileDTO fileDTO = ossService.queryFileInfo(fileName);
            list.add(fileDTO);
        }
        return list;
    }

    /**
     * 根据customerId 查附件记录
     * @param sessionId
     * @return
     */
    @Override
    public List<FileDTO> queryFileInfoBySessionId(String sessionId) {
        //1、根据SessionId查出本周期上传的的fileId
        List<String > listFileId = fileMgrService.getFileIdBySessionId(sessionId);
        //用于保存FileDTO
        List<FileDTO> list = new ArrayList<>();
        // 2、根据fileId查出sys_file中的filename
        for (String fileName:listFileId) {
            FileDTO fileDTO = ossService.queryFileInfo(fileName);
            list.add(fileDTO);
        }

        return list;
    }

    @Override
    public FileDTO queryFileInfoByFileName(String fileName) {
        return ossService.queryFileInfo(fileName);
    }


//    @Override
//    public List<FileDTO> queryFileInfo(String fileId) {
//        return ossService.listFileInfo(fileId);
//    }
}
