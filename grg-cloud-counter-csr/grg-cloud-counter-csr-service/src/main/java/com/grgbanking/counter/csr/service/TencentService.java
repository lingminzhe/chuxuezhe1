package com.grgbanking.counter.csr.service;

import com.tencentyun.TLSSigAPIv2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TencentService {

    @Value("${tencent.video.sdkAppId}")
    private Integer sdkAppId;

    @Value("${tencent.video.secretKey}")
    private String secretKey;

    public String getUserSig(String userId){
        TLSSigAPIv2 api = new TLSSigAPIv2(sdkAppId, secretKey);
        String userSig = api.genUserSig(userId, 180 * 86400);
        return userSig;
    }

}
