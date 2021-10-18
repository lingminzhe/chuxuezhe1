package com.grgbanking.counter.csr.controller;

import com.grgbanking.counter.common.core.util.Resp;
import com.grgbanking.counter.common.socket.lineup.service.LineupService;
import com.grgbanking.counter.common.socket.lineup.service.impl.LineupAbstractService;
import com.grgbanking.counter.csr.api.dto.UploadFileDTO;
import com.grgbanking.counter.csr.api.dubbo.RemoteCsrOssService;
import com.grgbanking.counter.csr.dto.GetFileDto;
import com.grgbanking.counter.csr.service.GrgFileManagerService;
import com.grgbanking.counter.iam.api.dubbo.RemoteSysFileService;
import com.grgbanking.counter.iam.api.entity.SysFileEntity;
import com.grgbanking.counter.oss.api.dto.FileDTO;
import com.grgbanking.counter.oss.api.dubbo.RemoteOssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: Ye Kaitao
 * @create: 2021-10-11
 */
@Api(value = "oss",tags = "座席文件存储")
@RestController
@RequestMapping("/oss")
public class OssController {
    
    @Autowired
    @Qualifier(value = "grgFileManagerService")
    private GrgFileManagerService fileManagerService;

    @DubboReference
    private RemoteOssService remoteOssService;

    @DubboReference
    private RemoteSysFileService sysFileService;

    @Autowired
    private LineupService lineupService;

    @Autowired
    private LineupAbstractService lineupAbstractService;

    @Autowired
    private RemoteCsrOssService csrOssService;

    /**
     *
     * @param params
     * @return
     */
    @Transactional
    @SneakyThrows
    @ApiOperation(value = "根据座席id获取客户身份证正反面接口")
    @PostMapping("/file/getIdCardImagesByEmployeeId")
    public Resp getIdCardImagesByEmployeeId(@RequestBody Map<String, String> params){
        //根据employeeId找出
        String employeeId = params.get("employeeId");
        String customerId = lineupService.findCustomer(employeeId);
        String sessionId = lineupAbstractService.findSessionId(customerId);
//        String sessionId = "4567890";
//        String customerId = "10002";
        //判断传入的customerId是否为空
        if (sessionId !=null){
            //根据customerId 找到对应的fileId
            List<FileDTO> list = fileManagerService.getBySessionId(sessionId);
            List<FileDTO> fileList = getFileDTO(list);
            if (fileList.size()==0){
                return Resp.success("查询的id没有附件记录");
            }
            return Resp.success(fileList);
        }else {
            return Resp.failed("传入的customerId不能为空");
        }
//        List<UploadFileDTO> fileDTO = remoteOssService.queryFileInfoByCustomerId(customerId);
    }

    /**
     * 传入参数  employeeId   fileBusiType
     * @param getFileDto
     * @return
     */
    @Transactional
    @SneakyThrows
    @ApiOperation(value = "根据座席id获取客户身份证正反面接口")
    @PostMapping("/file/getFiles")
    public Resp getFiles(@RequestBody GetFileDto fileDTO){

        String customerId = lineupService.findCustomer(fileDTO.getEmployeeId());
        List<String> busiType = fileDTO.getFileBusiType();

        List<UploadFileDTO> uploadFileDTO = csrOssService.getFileByCustomerId(customerId,busiType);

        if (uploadFileDTO==null){
            return Resp.failed("文件获取失败");
        }
        if (uploadFileDTO.size()==0){
            return Resp.success("查无此用户的附件信息");
        }

        return Resp.success(uploadFileDTO,"获取附件成功");
    }



    /**
     * 获取fileDto 附件信息
     * @param list
     * @return
     */
    private List<FileDTO> getFileDTO(List<FileDTO> list) {
        List<FileDTO> fileList = new ArrayList<>();
        for (FileDTO file : list) {
            SysFileEntity fileEntity = sysFileService.getFileByFileId(file.getFileId());
            String fileName = fileEntity.getFileName();
            FileDTO fileDTO =  remoteOssService.queryFileInfoByFileName(fileName);
            fileDTO.setFileBusiType(file.getFileBusiType());
            fileList.add(fileDTO);
        }
        return fileList;
    }

}
