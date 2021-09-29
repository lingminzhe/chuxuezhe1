package com.grgbanking.counter.common.core.util;

import cn.hutool.core.date.DatePattern;
import com.grgbanking.counter.common.core.constant.CommonConstants;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Socket数据传输统一参数head部分
 */
@Data
public class SocketParamHead implements Serializable {

    private String clientId;

    private String api_no;

    private String tran_time;

    private String biz_no;

    private int code = CommonConstants.SUCCESS;

    private String msg = CommonConstants.SUCCESS_MESSAGE;

    private static String getTranTime(){
        return DateUtil.format(new Date(), DatePattern.NORM_DATETIME_MS_PATTERN);
    }
    public SocketParamHead() {
    }

    public static SocketParamHead success() {
        return success(null);
    }

    public static SocketParamHead success(String apiNo) {
        return success(apiNo,null);
    }

    public static SocketParamHead success(String apiNo, String bizNo) {
        return success(apiNo, bizNo, getTranTime());
    }

    public static SocketParamHead success(String apiNo, String bizNo, String tranTime) {
        return result(apiNo, tranTime, bizNo, CommonConstants.SUCCESS, CommonConstants.SUCCESS_MESSAGE);
    }

    public static SocketParamHead success(String apiNo,int code,String msg) {
        return result(apiNo, getTranTime(), null, code, msg);
    }

    public static SocketParamHead failed() {
        return failed(null);
    }

    public static SocketParamHead failed(String apiNo) {
        return failed(apiNo,null);
    }

    public static SocketParamHead failed(String apiNo, String bizNo) {
        return failed(apiNo, bizNo, getTranTime(),CommonConstants.FAIL, CommonConstants.FAILURE_MESSAGE);
    }

    public static SocketParamHead failed(int code, String msg) {
        return failed(null, null,getTranTime(), code, msg);
    }

    public static SocketParamHead failed(String apiNo, String bizNo, String tranTime, int code, String msg) {
        return result(apiNo, tranTime, bizNo, code, msg);
    }

    private static SocketParamHead result(String apiNo, String tranTime, String bizNo, int code, String msg) {
        SocketParamHead head = new SocketParamHead();
        head.setApi_no(apiNo);
        head.setBiz_no(bizNo);
        head.setTran_time(tranTime);
        head.setCode(code);
        head.setMsg(msg);
        return head;
    }
}
