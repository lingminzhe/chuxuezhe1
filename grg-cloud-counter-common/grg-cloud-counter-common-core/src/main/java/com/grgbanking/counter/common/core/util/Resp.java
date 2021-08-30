/**
 * Copyright (c)2017-2020 GRGBanking All rights reserved.
 * <p>
 * https://www.grgbanking.com
 * <p>
 * 版权所有，侵权必究！
 */

package com.grgbanking.counter.common.core.util;

import com.grgbanking.counter.common.core.constant.CommonConstants;
import lombok.Builder;

import java.io.Serializable;

/**
 * 前端统一返回数据
 *
 * @author MARK xx@grgbanking.com
 */
@Builder
public class Resp<T> implements Serializable {

    private static final long serialVersionUID = -874100267531613669L;

    private int code;

    private String msg;

    private T data;

    public Resp() {
    }

    private Resp(T data) {
        this.code = CommonConstants.SUCCESS;
        this.msg = CommonConstants.SUCCESS_MESSAGE;
        this.data = data;
    }

    private Resp(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Resp(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public final static Integer SUCCESS = CommonConstants.SUCCESS;
    public final static Integer FAIL = CommonConstants.FAIL;

    public static Resp ok() {
        return new Resp(null);
    }

    public static Resp success() {
        return new Resp(200, CommonConstants.SUCCESS_MESSAGE);
    }

    public static <T> Resp<T> success(T data) {
        return new Resp(200, CommonConstants.SUCCESS_MESSAGE, data);
    }

    public static <T> Resp<T> ok(T data) {
        return new Resp(data);
    }

    public static Resp error() {
        return error(CommonConstants.FAIL, CommonConstants.FAILURE_MESSAGE);
    }

    public static Resp error(String msg) {
        return error(CommonConstants.FAIL, msg);
    }

    public static <T> Resp<T> error(String msg, T data) {
        return error(CommonConstants.FAIL, msg, data);
    }

    public static Resp error(int code, String msg) {
        return new Resp(code, msg);
    }

    public static <T> Resp<T> error(int code, String msg, T data) {
        return new Resp(code, msg, data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Resp{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
