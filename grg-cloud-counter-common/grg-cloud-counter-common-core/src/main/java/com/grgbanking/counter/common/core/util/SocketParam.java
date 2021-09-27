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
        return success(SocketParamHead.success());
    }

    public static <T> SocketParam<T> success(SocketParamHead head) {
        return success(head,null);
    }

    public static <T> SocketParam<T> success(T body) {
        return success(SocketParamHead.success(), body);
    }

    public static <T> SocketParam<T> success(SocketParamHead head, T body) {
        return result(head, body);
    }

    public static <T> SocketParam<T> failed() {
        return failed(SocketParamHead.failed(),null);
    }

    public static <T> SocketParam<T> failed(String msg) {
        return failed(CommonConstants.FAIL, msg);
    }

    public static <T> SocketParam<T> failed(int code,String msg) {
        return failed(SocketParamHead.failed(code,msg), null);
    }

    public static <T> SocketParam<T> failed(T body) {
        return failed(SocketParamHead.failed(),body);
    }

    public static <T> SocketParam<T> failed(SocketParamHead head, T body) {
        return result(head, body);
    }

    private static <T> SocketParam<T> result(SocketParamHead head, T body) {
        SocketParam<T> result = new SocketParam<>();
        result.setHead(head);
        result.setBody(body);
        return result;
    }

}
