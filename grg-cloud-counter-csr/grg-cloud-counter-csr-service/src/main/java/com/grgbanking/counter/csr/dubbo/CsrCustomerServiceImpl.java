package com.grgbanking.counter.csr.dubbo;

import com.grgbanking.counter.csr.api.service.CsrCustomerRemoteService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

@Slf4j
@DubboService
public class CsrCustomerServiceImpl implements CsrCustomerRemoteService {

    @Override
    public String queryCutomer(String username) {
        log.info("查询用户：{}",username);
        return username;
    }

}
