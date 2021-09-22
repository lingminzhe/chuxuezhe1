/**
 * Copyright (c)2021 GRGBanking All rights reserved.
 * <p>
 * https://www.grgbanking.com
 * <p>
 * 版权所有，侵权必究！
 */

package com.grgbanking.counter.common.core.util;

import cn.hutool.core.date.DatePattern;
import com.grgbanking.counter.common.core.constant.CommonConstants;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * Socket数据传输统一参数
 *
 * @author MARK xx@grgbanking.com
 */
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class SocketParam<T> implements Serializable {

    private static final long serialVersionUID = -874100267531613669L;

    @Getter
    @Setter
    private SocketParamHead head;

    @Getter
    @Setter
    private T body;

    public static <T> SocketParam<T> success() {
        return result(null, DateUtil.format(new Date(), DatePattern.NORM_DATETIME_MS_PATTERN), null, null,null, null, null, CommonConstants.SUCCESS, CommonConstants.SUCCESS_MESSAGE, null);
    }

    public static <T> SocketParam<T> success(T body) {
        return result(null, DateUtil.format(new Date(), DatePattern.NORM_DATETIME_MS_PATTERN), null, null,null, null, null, CommonConstants.SUCCESS, CommonConstants.SUCCESS_MESSAGE, body);
    }

    public static <T> SocketParam<T> failed() {
        return result(null, DateUtil.format(new Date(), DatePattern.NORM_DATETIME_MS_PATTERN), null, null,null, null, null, CommonConstants.FAIL, CommonConstants.FAILURE_MESSAGE, null);
    }

    public static <T> SocketParam<T> failed(String msg) {
        return result(null, DateUtil.format(new Date(), DatePattern.NORM_DATETIME_MS_PATTERN), null, null,null, null, null, CommonConstants.FAIL, msg, null);
    }

    public static <T> SocketParam<T> failed(T body) {
        return result(null, DateUtil.format(new Date(), DatePattern.NORM_DATETIME_MS_PATTERN), null, null,null, null, null, CommonConstants.FAIL, CommonConstants.FAILURE_MESSAGE, body);
    }

    public static <T> SocketParam<T> failed(String apiNo, String tranTime, String bizNo, int code, String msg) {
        return result(apiNo, tranTime, bizNo, null, null, null, null,code, msg, null);
    }

    public static <T> SocketParam<T> result(String apiNo, String tranTime, String bizNo, String user_login_type, String user_login_id, String service_session_id,String tokenId, int code, String msg, T body) {
        SocketParam<T> result = new SocketParam<>();
        result.setHead(new SocketParamHead(apiNo, tranTime, bizNo, user_login_type, user_login_id,service_session_id,tokenId, code, msg));
        result.setBody(body);
        return result;
    }

}
