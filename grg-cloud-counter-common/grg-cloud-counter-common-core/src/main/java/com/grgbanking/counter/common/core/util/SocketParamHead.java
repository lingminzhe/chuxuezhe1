package com.grgbanking.counter.common.core.util;

import com.grgbanking.counter.common.core.constant.CommonConstants;
import lombok.Data;

import java.io.Serializable;

@Data
public class SocketParamHead implements Serializable {

    private String apiNo;

    private String tranTime;

    private String bizNo;

    private String userLoginType;

    private String userLoginId;

    private String serviceSessionId;

    private String tokenId;

    private int code = CommonConstants.SUCCESS;

    private String msg = CommonConstants.SUCCESS_MESSAGE;

    public SocketParamHead() {
    }

    public SocketParamHead(String apiNo, String tranTime, String bizNo, String userLoginType, String userLoginId,String serviceSessionId,String tokenId, int code, String msg) {
        this.apiNo = apiNo;
        this.tranTime = tranTime;
        this.bizNo = bizNo;
        this.userLoginId = userLoginType;
        this.userLoginId = userLoginId;
        this.serviceSessionId = serviceSessionId;
        this.tokenId = tokenId;
        this.code = code;
        this.msg = msg;
    }

}
