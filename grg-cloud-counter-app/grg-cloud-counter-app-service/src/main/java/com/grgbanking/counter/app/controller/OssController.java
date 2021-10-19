package com.grgbanking.counter.app.controller;

import com.alibaba.nacos.common.utils.StringUtils;
import com.grgbanking.counter.app.dto.FileInfoDTO;
import com.grgbanking.counter.app.dto.UploadFileDTO;
import com.grgbanking.counter.app.vo.SimpleCustomerVo;
import com.grgbanking.counter.bank.api.dubbo.RemoteCusInfoService;
import com.grgbanking.counter.common.core.constant.FileBusiTypeConstants;
import com.grgbanking.counter.common.core.util.FileUtil;
import com.grgbanking.counter.common.core.util.Resp;
import com.grgbanking.counter.common.socket.lineup.service.impl.LineupAbstractService;
import com.grgbanking.counter.csr.api.entity.GrgFileMgrEntity;
import com.grgbanking.counter.oss.api.dto.FileDTO;
import com.grgbanking.counter.oss.api.dubbo.RemoteOssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * aws 对外提供服务端点
 */
@Slf4j
@RestController
@Api(value = "app", tags = "文件上传管理模块")
@RequestMapping("/file")
public class OssController {

    @DubboReference
    private RemoteOssService remoteOssService;

    @DubboReference
    private RemoteCusInfoService remoteCusInfoService;

    @Autowired
    private LineupAbstractService lineupAbstractService;

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    @SneakyThrows
    @ApiOperation(value = "文件上传接口")
    @PostMapping("/upload")
    public Resp upload(@RequestBody MultipartFile file, GrgFileMgrEntity grgFileMgrEntity, String createUser,SimpleCustomerVo grgCustomerVo) {

        String sessionId = lineupAbstractService.findSessionId(grgCustomerVo.getCustomerId());
        if (StringUtils.isNotBlank(sessionId)) {
            grgFileMgrEntity.setSessionId(sessionId);
        }else {
            //临时id
//            grgFileMgrEntity.setSessionId("123456789");
            return Resp.failed("sessionId为空，请联系管理员");
        }
        FileDTO fileDTO = ossUploadFile(file, grgFileMgrEntity, createUser);

        return Resp.success(fileDTO,"文件上传成功");

    }

    /**
     * 上传身份证正反面 及识别到的身份证信息
     * @param grgCustomerVo
     * @return
     */
    @Transactional
    @SneakyThrows
    @ApiOperation(value = "上传身份证正反面接口",tags = "上传身份证正反面接口 获取上传图片url 。若传入的身份证在数据库上有记录，则获取该记录")
    @PostMapping("/uploadIDCard")
    public Resp uploadIdCard(@RequestBody SimpleCustomerVo grgCustomerVo) {
        if(null==grgCustomerVo.getFile1() || null==grgCustomerVo.getFile2()){
            return Resp.failed("需上传身份证正反面");
        }
        //获取身份证正反面
        byte[] decode1 = Base64.getDecoder().decode(grgCustomerVo.getFile1());
        MultipartFile file1 = getMultipartFile(decode1);
        byte[] decode2 = Base64.getDecoder().decode(grgCustomerVo.getFile2());
        MultipartFile file2 = getMultipartFile(decode2);

        //获取sessionId
        // 开发时使用的假数据
        GrgFileMgrEntity grgFileMgrEntity = new GrgFileMgrEntity();
//        grgFileMgrEntity.setSessionId("100001");
        String sessionId = lineupAbstractService.findSessionId(grgCustomerVo.getCustomerId());
        if (StringUtils.isNotBlank(sessionId)) {
            grgFileMgrEntity.setSessionId(sessionId);
        }else {
            //临时id
            grgFileMgrEntity.setSessionId("4567890");
//            return Resp.failed("sessionId为空，请联系管理员");
        }
        //身份证正面
        grgFileMgrEntity.setFileBusiType(FileBusiTypeConstants.ID_CARD_FRONT);
//        grgFileMgrEntity.setCustomerId(grgCustomerVo.getCustomerId());
        com.grgbanking.counter.oss.api.dto.FileDTO uploadFile1 = ossUploadFile(file1, grgFileMgrEntity, grgCustomerVo.getCreateUser());
        //身份证反面
        grgFileMgrEntity.setFileBusiType(FileBusiTypeConstants.ID_CARD_BEHIND);
        com.grgbanking.counter.oss.api.dto.FileDTO uploadFile2 = ossUploadFile(file2, grgFileMgrEntity, grgCustomerVo.getCreateUser());
        log.info("文件上传成功,文件名为:{}{}"+uploadFile1.getFileName(),uploadFile2.getFileName());
        List<FileDTO> list = new ArrayList();
        list.add(uploadFile1);
        list.add(uploadFile2);

        return Resp.success(list,"上传成功");
    }

    @SneakyThrows
    @ApiOperation(value = "文件上传接口")
    @PostMapping("/uploadFile")
    public Resp uploadFile(@RequestBody UploadFileDTO fileDto){

        if (fileDto==null||fileDto.getFileDTO()==null){
            return Resp.failed("需传入文件和文件类型");
        }

        for (FileInfoDTO file:fileDto.getFileDTO()) {
            GrgFileMgrEntity grgFileMgrEntity = new GrgFileMgrEntity();
            //1、获取Base64的文件
            String fileBase64 = file.getFile();
            byte[] decode1 = Base64.getDecoder().decode(fileBase64);
            MultipartFile file1 = getMultipartFile(decode1);
            //2、获取文件类型
            String fileBusiType = file.getFileBusiType();
            grgFileMgrEntity.setFileBusiType(fileBusiType);
            //3、获取sessionId
            // 开发时使用的假数据
//            String sessionId = "1019";
            String sessionId = lineupAbstractService.findSessionId(fileDto.getCustomerId());
            grgFileMgrEntity.setSessionId(sessionId);
            grgFileMgrEntity.setCustomerId(fileDto.getCustomerId());
            //TODO 创建者
            ossUploadFile(file1, grgFileMgrEntity, "");
        }

        return Resp.success("上传成功");
    }


    /**
     * Base64转MultipartFile
     * @param decode1
     * @return
     * @throws IOException
     */
    private MultipartFile getMultipartFile(byte[] decode1) throws IOException {
        InputStream inputStream = new ByteArrayInputStream(decode1);
        return new MockMultipartFile(ContentType.APPLICATION_OCTET_STREAM.toString(), inputStream);
    }

    /**
      * 公共参数抽取成一个方法
      * @param file
      * @param grgFileMgrEntity
      * @param createUser
      */
    @SneakyThrows
    public FileDTO ossUploadFile(MultipartFile file, GrgFileMgrEntity grgFileMgrEntity, String createUser){
        byte[] fileByte = IOUtils.toByteArray(file.getInputStream());
        String md5 = FileUtil.getFileMd5(file);
        String original = file.getOriginalFilename();
        long size = file.getSize();
        String contentType = file.getContentType();
        FileDTO upload = remoteOssService.upload(fileByte,md5, original, size, contentType, grgFileMgrEntity, createUser);
        return upload;
    }

}
