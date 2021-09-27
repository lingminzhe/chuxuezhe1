package com.grgbanking.counter.common.socket.socket.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SocketConnectStatusEnum {

    /**
     * 上线
     */
    UP("1", "UP"),

    /**
     * 下线
     */
    DOWN("0", "DOWN");

    /**
     * 类型
     */
    private String type;

    /**
     * 描述
     */
    private String description;

}