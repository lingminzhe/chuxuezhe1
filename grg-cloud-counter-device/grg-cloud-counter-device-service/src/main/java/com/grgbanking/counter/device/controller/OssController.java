package com.grgbanking.counter.device.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.common.utils.StringUtils;
import com.grgbanking.counter.device.dto.FileInfoDTO;
import com.grgbanking.counter.device.dto.UploadFileDTO;
import com.grgbanking.counter.device.vo.SimpleCustomerVo;
import com.grgbanking.counter.bank.api.dubbo.RemoteCusInfoService;
import com.grgbanking.counter.common.core.constant.FileBusiTypeConstants;
import com.grgbanking.counter.common.core.util.FileUtil;
import com.grgbanking.counter.common.core.util.Resp;
import com.grgbanking.counter.common.core.util.SocketParam;
import com.grgbanking.counter.common.socket.broadcast.constant.RedisBroadcastConstants;
import com.grgbanking.counter.common.socket.broadcast.service.RedisBroadcastService;
import com.grgbanking.counter.common.socket.lineup.service.LineupService;
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
import java.util.HashMap;
import java.util.List;

/**
 * aws ????????????????????????
 */
@Slf4j
@RestController
@Api(value = "device", tags = "????????????????????????")
@RequestMapping("/file")
public class OssController {

    @DubboReference
    private RemoteOssService remoteOssService;

    @DubboReference
    private RemoteCusInfoService remoteCusInfoService;

    @Autowired
    private LineupAbstractService lineupAbstractService;

    @Autowired
    private RedisBroadcastService broadcastService;

    @Autowired
    private LineupService lineupService;

    /**
     * ????????????
     *
     * @param file
     * @return
     */
    @Deprecated
    @SneakyThrows
    @ApiOperation(value = "??????????????????")
    @PostMapping("/upload")
    public Resp upload(@RequestBody MultipartFile file, GrgFileMgrEntity grgFileMgrEntity, String createUser, SimpleCustomerVo grgCustomerVo) {

        String sessionId = lineupAbstractService.findSessionId(grgCustomerVo.getCustomerId());
        if (StringUtils.isNotBlank(sessionId)) {
            grgFileMgrEntity.setSessionId(sessionId);
        } else {
            //??????id
//            grgFileMgrEntity.setSessionId("123456789");
            return Resp.failed("sessionId???????????????????????????");
        }
        FileDTO fileDTO = ossUploadFile(file, grgFileMgrEntity, createUser,"");

        return Resp.success(fileDTO, "??????????????????");

    }

    /**
     * ???????????????????????? ??????????????????????????????
     *
     * @param grgCustomerVo
     * @return
     */
    @Transactional
    @SneakyThrows
    @ApiOperation(value = "??????????????????????????????", tags = "?????????????????????????????? ??????????????????url ?????????????????????????????????????????????????????????????????????")
    @PostMapping("/uploadIDCard")
    public Resp uploadIdCard(@RequestBody SimpleCustomerVo grgCustomerVo) {
        HashMap<Object, Object> map = new HashMap<>();
        ArrayList<Object> returnlist = new ArrayList<>();
        String identifyNumber = grgCustomerVo.getIdentifyNumber();
        map.put("idCard", identifyNumber);
        if (null == grgCustomerVo.getFile1() || null == grgCustomerVo.getFile2()) {
            return Resp.failed("???????????????????????????");
        }
        //????????????????????????
        byte[] decode1 = Base64.getDecoder().decode(grgCustomerVo.getFile1());
        MultipartFile file1 = getMultipartFile(decode1);
        byte[] decode2 = Base64.getDecoder().decode(grgCustomerVo.getFile2());
        MultipartFile file2 = getMultipartFile(decode2);
        //??????sessionId
        // ???????????????????????????
        GrgFileMgrEntity grgFileMgrEntity = new GrgFileMgrEntity();

//      String sessionId ="1000";
        String sessionId = lineupAbstractService.findSessionId(grgCustomerVo.getCustomerId());
        if (StringUtils.isNotBlank(sessionId)) {
            grgFileMgrEntity.setSessionId(sessionId);
        } else {
            //??????id
//            grgFileMgrEntity.setSessionId("4567890");
            return Resp.failed("sessionId???????????????????????????");
        }
        //???????????????????????????
        grgFileMgrEntity.setFileBusiType(FileBusiTypeConstants.ID_CARD_FRONT);
//        grgFileMgrEntity.setCustomerId(grgCustomerVo.getCustomerId());
        FileDTO uploadFile1 = ossUploadFile(file1, grgFileMgrEntity, grgCustomerVo.getCreateUser(),"");
        returnlist.add(uploadFile1.getFileName());
        //???????????????????????????
        grgFileMgrEntity.setFileBusiType(FileBusiTypeConstants.ID_CARD_BEHIND);
        FileDTO uploadFile2 = ossUploadFile(file2, grgFileMgrEntity, grgCustomerVo.getCreateUser(),"");
        returnlist.add(uploadFile2.getFileName());
        log.info("??????????????????,????????????:{}{}" + uploadFile1.getFileName(), uploadFile2.getFileName());
        List<FileDTO> list = new ArrayList();
        list.add(uploadFile1);
        list.add(uploadFile2);
        map.put("fileName", returnlist);
        map.put("result","success");
        String employee = lineupService.findEmployee(grgCustomerVo.getCustomerId());
        SocketParam param = lineupService.successParam(employee, "idCard", "120001",map);
        broadcastService.sendBroadcast(RedisBroadcastConstants.BROADCAST_CHANNEL_CSR, param);
        log.info("???????????????????????????: {}", JSON.toJSONString(param));
        return Resp.success(list, "????????????");
    }

    @SneakyThrows
    @ApiOperation(value = "??????????????????")
    @PostMapping("/uploadFile")
    public Resp uploadFile(@RequestBody UploadFileDTO fileDto) {
        HashMap<Object, Object> map = new HashMap<>();
        if (fileDto == null || fileDto.getFileDTO() == null) {
            return Resp.failed("??????????????????????????????");
        }
        List<FileDTO> list = new ArrayList();
        ArrayList<Object> resultList = new ArrayList<>();

        for (FileInfoDTO file : fileDto.getFileDTO()) {
            GrgFileMgrEntity grgFileMgrEntity = new GrgFileMgrEntity();
            //1?????????Base64?????????
            String fileBase64 = file.getFile();
            byte[] decode1 = Base64.getDecoder().decode(fileBase64);
            MultipartFile file1 = getMultipartFile(decode1);
            //2???????????????????????????
            String fileBusiType = file.getFileBusiType();
            grgFileMgrEntity.setFileBusiType(fileBusiType);
            //3???????????????
            String fileType = file.getFileType();

            //4?????????sessionId
            // ???????????????????????????
//            String sessionId = "1020";
            String sessionId = lineupAbstractService.findSessionId(fileDto.getUserId());
            grgFileMgrEntity.setSessionId(sessionId);
            //TODO ???????????????????????????????????????customerId????????????????????????
//            grgFileMgrEntity.setCustomerId(fileDto.getCustomerId());
            FileDTO fileDTO = ossUploadFile(file1, grgFileMgrEntity, "",fileType);
            resultList.add(fileDTO.getFileName());
            list.add(fileDTO);
        }
        map.put("fileName",resultList);
        map.put("result","success");
        map.put("server","device");
        String employee = lineupService.findEmployee(fileDto.getUserId());
        SocketParam param = lineupService.successParam(employee, "vertifyTransaction", "120001",map);
        broadcastService.sendBroadcast(RedisBroadcastConstants.BROADCAST_CHANNEL_CSR, param);
        return Resp.success(list, "????????????");
    }


    /**
     * Base64???MultipartFile
     *
     * @param decode1
     * @return
     * @throws IOException
     */
    private MultipartFile getMultipartFile(byte[] decode1) throws IOException {
        InputStream inputStream = new ByteArrayInputStream(decode1);
        return new MockMultipartFile(ContentType.APPLICATION_OCTET_STREAM.toString(), inputStream);
    }

    /**
     * ?????????????????????????????????
     *
     * @param file
     * @param grgFileMgrEntity
     * @param createUser
     */
    @SneakyThrows
    public FileDTO ossUploadFile(MultipartFile file, GrgFileMgrEntity grgFileMgrEntity, String createUser,String fileType) {
        byte[] fileByte = IOUtils.toByteArray(file.getInputStream());
        String md5 = FileUtil.getFileMd5(file);
        String original = file.getOriginalFilename();
        long size = file.getSize();
        //????????????????????? ?????????svg?????? ???????????????????????????
        String contentType = "";
        if ("svg".equals(fileType)) {
            contentType = "svg";
        }
//        String contentType = file.getContentType();

        FileDTO upload = remoteOssService.upload(fileByte, md5, original, size, contentType, grgFileMgrEntity, createUser);
        return upload;
    }

}
