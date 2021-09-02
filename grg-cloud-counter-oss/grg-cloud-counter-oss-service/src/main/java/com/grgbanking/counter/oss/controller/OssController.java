package com.grgbanking.counter.oss.controller;

import com.grgbanking.counter.common.core.util.FileUtil;
import com.grgbanking.counter.common.core.util.Resp;
import com.grgbanking.counter.common.data.annotation.MultiRequestBody;
import com.grgbanking.counter.oss.service.OssService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.amazonaws.util.IOUtils;

import java.util.Map;

/**
 * aws 对外提供服务端点
 */
@RestController
@AllArgsConstructor
@RequestMapping("/oss")
public class OssController {

    private final OssService ossService;


    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    @SneakyThrows
    @PostMapping("/upload")
    public Resp upload(@RequestBody MultipartFile file) {
        byte[] fileByte = IOUtils.toByteArray(file.getInputStream());
        String md5 = FileUtil.getFileMd5(file);
        String original = file.getOriginalFilename();
        long size = file.getSize();
        String contentType = file.getContentType();
        return Resp.success(ossService.upload(fileByte, md5, original, size, contentType));
    }

    /**
     * 查询列表
     *
     * @param fileType
     * @param userId
     * @return
     */
    @SneakyThrows
    @GetMapping("/list")
    public Resp list(@MultiRequestBody String fileType, @MultiRequestBody String userId) {
        return Resp.success(ossService.list(fileType, userId));

    }

    /**
     * 查询单个文件
     *
     * @param fileName
     * @return
     */

    @SneakyThrows
    @GetMapping("/info/{fileName}")
    public Resp queryFileInfo(@PathVariable String fileName) {
        return Resp.success(ossService.queryFileInfo(fileName));
    }

    /**
     * 删除文件
     *
     * @param fileName
     * @return
     */
    @SneakyThrows
    @GetMapping("/delete/{fileName}")
    public Resp deleteFile(@PathVariable String fileName) {
        return Resp.success(ossService.deleteFile(fileName));
    }

}
