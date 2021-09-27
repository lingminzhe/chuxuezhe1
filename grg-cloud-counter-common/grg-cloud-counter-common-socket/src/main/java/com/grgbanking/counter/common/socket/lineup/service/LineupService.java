package com.grgbanking.counter.common.socket.lineup.service;

import com.grgbanking.counter.common.socket.socket.constant.SocketConnectStatusEnum;

/**
 * 排队处理Service
 */
public interface LineupService {

    /**
     * 终端登录
     * @param clientId
     */
    void login(String clientId);


    /**
     * 正常结束视频通话
     * @param clientId
     */
    void finish(String clientId);

    /**
     * 排队总人数
     * @return
     */
    Long rank();

    /**
     * 检查是否有用户正在排队
     */
    void check();

    /**
     * 提醒有人在排队
     */
    void remind();

    /**
     * 连接状态发生变化
     *
     * @param clientId
     * @return
     */
    boolean connectionChanged(String clientId, SocketConnectStatusEnum statusEnum);

}
