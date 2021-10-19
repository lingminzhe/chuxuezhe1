package com.grgbanking.counter.csr.service.impl;

import com.grgbanking.counter.common.socket.lineup.service.LineupService;
import com.grgbanking.counter.common.socket.lineup.service.impl.LineupAbstractService;
import com.grgbanking.counter.csr.api.dto.UploadFileDTO;
import com.grgbanking.counter.csr.api.dubbo.RemoteCsrOssService;
import com.grgbanking.counter.csr.service.GrgFileManagerService;
import com.grgbanking.counter.iam.api.dubbo.RemoteSysFileService;
import com.grgbanking.counter.iam.api.entity.SysFileEntity;
import com.grgbanking.counter.oss.api.dto.FileDTO;
import com.grgbanking.counter.oss.api.dubbo.RemoteOssService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Ye Kaitao
 * @create: 2021-10-18
 */
@Slf4j
@Service
public class RemoteCsrOssServiceImpl implements RemoteCsrOssService {
    @Autowired
    private LineupService lineupService;

    @Autowired
    private LineupAbstractService lineupAbstractService;

    @Autowired
    @Qualifier(value = "grgFileManagerService")
    private GrgFileManagerService fileManagerService;

    @DubboReference
    private RemoteOssService remoteOssService;

    @DubboReference
    private RemoteSysFileService sysFileService;

    @Override
    public List<UploadFileDTO> getFileByCustomerId(String customerId, List<String> busiType) {

        String sessionId = lineupAbstractService.findSessionId(customerId);
//        String sessionId = "1018";
        if (sessionId !=null){
            //根据customerId 找到对应的fileId
            List<FileDTO> list = fileManagerService.getBySessionIdAndType(sessionId,busiType);
            List<UploadFileDTO> fileList = getFileDTO(list);

            return fileList;
        }else {
            log.error("sessionId为空");
            return null;
        }
    }

    /**
     * 获取fileDto 附件信息
     * @param list
     * @return
     */
    private List<UploadFileDTO> getFileDTO(List<FileDTO> list) {
        List<UploadFileDTO> fileList = new ArrayList<>();
        for (FileDTO file : list) {
            SysFileEntity fileEntity = sysFileService.getFileByFileId(file.getFileId());
            String fileName = fileEntity.getFileName();
            FileDTO fileDTO =  remoteOssService.queryFileInfoByFileName(fileName);
            fileDTO.setFileBusiType(file.getFileBusiType());
            UploadFileDTO uploadFileDTO = new UploadFileDTO();
            BeanUtils.copyProperties(fileDTO,uploadFileDTO);
            fileList.add(uploadFileDTO);
        }
        return fileList;
    }

}
