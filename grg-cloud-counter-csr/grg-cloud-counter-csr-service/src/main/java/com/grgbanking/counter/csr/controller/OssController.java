package com.grgbanking.counter.csr.controller;

import com.grgbanking.counter.common.core.util.Resp;
import com.grgbanking.counter.oss.api.dto.FileDTO;
import com.grgbanking.counter.oss.api.dubbo.RemoteOssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Ye Kaitao
 * @create: 2021-10-11
 */
@Api(value = "oss",tags = "座席文件存储")
@RestController
@RequestMapping("/oss")
public class OssController {

    @DubboReference
    private RemoteOssService remoteOssService;

    @Transactional
    @SneakyThrows
    @ApiOperation(value = "获取身份证正反面接口")
    @PostMapping("/file/getIdCardInfo")
    public Resp getIdCardInfo(@RequestBody String fileName){
        FileDTO fileDTO = remoteOssService.queryFileInfo(fileName);

        return Resp.success(fileDTO);
    }
}
