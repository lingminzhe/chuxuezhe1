package com.grgbanking.counter.video.controller;

import com.grgbanking.counter.csr.api.service.CsrCustomerRemoteService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * dubbo调用例子，video服务通过dubbo远程调用csr服务的接口
 */
@RestController
public class VideoController {

    @DubboReference
    private CsrCustomerRemoteService csrCustomerRemoteService;

    @GetMapping("query")
    public String query(String username){
        return csrCustomerRemoteService.queryCutomer(username);
    }

}
