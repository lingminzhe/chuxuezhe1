package com.grgbanking.counter.csr.dubbo;

import com.grgbanking.counter.csr.api.service.CsrCustomerRemoteService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.cluster.router.condition.ConditionRouter;
import org.apache.dubbo.rpc.cluster.router.tag.TagRouter;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
@DubboService
public class CsrCustomerServiceImpl implements CsrCustomerRemoteService {

    @Value("${server.port}")
    private int port;

    @Override
    public int queryCutomer() {
        ConditionRouter s;
        TagRouter t;
        return port;
    }

}
