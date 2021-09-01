/**
 * Copyright (c)2021 GRGBanking All rights reserved.
 * <p>
 * https://www.grgbanking.com
 * <p>
 * 版权所有，侵权必究！
 */

package com.grgbanking.counter.common.core.util;

import com.grgbanking.counter.common.core.constant.CommonConstants;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 前端统一返回数据
 *
 * @author MARK xx@grgbanking.com
 */
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Resp<T> implements Serializable {

    private static final long serialVersionUID = -874100267531613669L;
    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private String msg;

    @Getter
    @Setter
    private T data;

    public static <T> Resp<T> success() {
        return restResult(null, CommonConstants.SUCCESS, null);
    }

    public static <T> Resp<T> success(T data) {
        return restResult(data, CommonConstants.SUCCESS, null);
    }

    public static <T> Resp<T> success(T data, String msg) {
        return restResult(data, CommonConstants.SUCCESS, msg);
    }

    public static <T> Resp<T> failed() {
        return restResult(null, CommonConstants.FAIL, null);
    }

    public static <T> Resp<T> failed(String msg) {
        return restResult(null, CommonConstants.FAIL, msg);
    }

    public static <T> Resp<T> failed(T data) {
        return restResult(data, CommonConstants.FAIL, null);
    }

    public static <T> Resp<T> failed(T data, String msg) {
        return restResult(data, CommonConstants.FAIL, msg);
    }

    private static <T> Resp<T> restResult(T data, int code, String msg) {
        Resp<T> apiResult = new Resp<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }
}
