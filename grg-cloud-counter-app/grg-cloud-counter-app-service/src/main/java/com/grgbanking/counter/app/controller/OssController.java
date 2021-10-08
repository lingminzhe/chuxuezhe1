package com.grgbanking.counter.app.controller;

import com.grgbanking.counter.app.vo.GrgCustomerVo;
import com.grgbanking.counter.bank.api.dubbo.RemoteCusInfoService;
import com.grgbanking.counter.bank.api.entity.GrgCusInfoEntity;
import com.grgbanking.counter.common.core.util.FileUtil;
import com.grgbanking.counter.common.core.util.Resp;
import com.grgbanking.counter.csr.api.entity.GrgFileMgrEntity;
import com.grgbanking.counter.oss.api.dubbo.RemoteOssService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * aws 对外提供服务端点
 */
@Slf4j
@RestController
@RequestMapping("/app")
public class OssController {

    @DubboReference
    private RemoteOssService remoteOssService;

    @DubboReference
    private RemoteCusInfoService remoteCusInfoService;

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    @SneakyThrows
    @PostMapping("/file/upload")
    public Resp upload(@RequestBody MultipartFile file, GrgFileMgrEntity grgFileMgrEntity, String createUser) {
        byte[] fileByte = IOUtils.toByteArray(file.getInputStream());
        String md5 = FileUtil.getFileMd5(file);
        String original = file.getOriginalFilename();
        long size = file.getSize();
        String contentType = file.getContentType();
        return Resp.success(remoteOssService.upload(fileByte, md5, original, size, contentType, grgFileMgrEntity,createUser));
    }

    /**
     * 上传身份证正反面 及识别到的身份证信息
     * @param file1 身份证正面
     * @param file2 身份证反面
     * @param grgFileMgrEntity
     * @param createUser
     * @return
     */
    @Transactional
    @SneakyThrows
    @PostMapping("/file/uploadIDCard")
    public Resp uploadIDCard(@RequestBody MultipartFile file1, MultipartFile file2, GrgFileMgrEntity grgFileMgrEntity, String createUser, GrgCustomerVo grgCustomerVo) {
        if(file1.isEmpty()&& file2.isEmpty()) {
            return Resp.failed("需上传身份证正反面");
        }
        uploadFile(file1, grgFileMgrEntity, createUser);
        uploadFile(file2, grgFileMgrEntity, createUser);
        log.info("文件上传成功");

        //1、判断身份证号码是否为空
        if (grgCustomerVo.getIdentifynumber()!=null ) {
            //2、若身份证号码不为空 根据该身份证查询数据库是否有记录
            GrgCusInfoEntity entity = remoteCusInfoService.getByCardNoOrIdNo(grgCustomerVo.getIdentifynumber());
            //2.1 若有记录则直接返回该用户数据
            if (entity!=null){
                return Resp.success(entity,"用户信息");
            }else {
                //TODO 2.2系统没有该用户信息 将该用户信息存入系统
                log.info("录入新用户信息");

            }
        }else {
            return Resp.failed("身份证号码为空");
        }
        return Resp.success();
    }

    @SneakyThrows
    public void uploadFile( MultipartFile file, GrgFileMgrEntity grgFileMgrEntity, String createUser){
        byte[] fileByte = IOUtils.toByteArray(file.getInputStream());
        String md5 = FileUtil.getFileMd5(file);
        String original = file.getOriginalFilename();
        long size = file.getSize();
        String contentType = file.getContentType();
        remoteOssService.upload(fileByte, md5, original, size, contentType, grgFileMgrEntity,createUser);
    }

}
