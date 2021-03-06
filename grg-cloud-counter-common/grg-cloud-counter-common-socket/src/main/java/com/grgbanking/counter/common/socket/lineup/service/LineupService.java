package com.grgbanking.counter.common.socket.lineup.service;

import com.grgbanking.counter.common.core.util.SocketParam;
import com.grgbanking.counter.common.socket.socket.constant.SocketConnectStatusEnum;

import java.util.Map;

/**
 * 排队处理Service
 */
public interface LineupService {

    /**
     * 公共失败param
     */
    SocketParam failedParam(String employee, String apiNo, String busiNo, Map map);

    /**
     * 公共成功param
     */
    SocketParam successParam(String employee, String apiNo, String busiNo, Map map);

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

    /**
     * 找出当前业务流程会话id
     * @param clientId
     * @return
     */
    String findSessionId(String clientId);

    /**
     * 结束当前会话
     * @param clientId
     * @return
     */
    Boolean finishSession(String clientId);

}
