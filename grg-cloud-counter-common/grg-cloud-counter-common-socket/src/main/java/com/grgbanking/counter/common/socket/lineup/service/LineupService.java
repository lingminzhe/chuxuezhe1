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

    /**
     * 找出服务我的坐席
     * @param clientId  用户id
     * @return
     */
    String findEmployee(String clientId);

    /**
     * 找出我服务的用户
     * @param clientId  坐席id
     * @return
     */
    String findCustomer(String clientId);
}
