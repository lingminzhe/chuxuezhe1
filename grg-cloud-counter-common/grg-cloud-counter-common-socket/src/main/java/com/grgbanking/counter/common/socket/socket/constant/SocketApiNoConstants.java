package com.grgbanking.counter.common.socket.socket.constant;

/**
 * Socket通讯中所有的apiNo定义在此
 */
public class SocketApiNoConstants {

    /**
     * 默认消息处理器名字
     */
    public static final String DEFAULT_HANDLER_NAME = "default";

    /**
     * Socket连接异常通知（包括上下线）
     */
    public static final String CONNECTION_STATUS_CHANGE = "connection_status_change";

    /**
     * 视频呼叫
     */
    public static final String VIDEO_CMD = "video_cmd";

    /**
     * 用于检查是否有用户正在排队视频呼叫
     */
    public static final String CUSTOMER_VIDEO_LINEUP_CHECK = "customer_video_lineup_check";

    /**
     * 消息确认
     */
    public static final String MODIFY_INFO = "modify_info";

    /**
     * 人脸识别
     */
    public static final String FACE_RECOGNITION = "face_recognition";

    /**
     * 查看我的视频呼叫排队的名次
     */
    public static final String QUERY_WAITING_RANK = "query_waiting_rank";

    /**
     * 激活码校验
     */
    public static final String ACTIVATION_CHECK = "activation_check";

    /**
     * 验证码校验
     */
    public static final String AUTH_CHECK = "auth_check";
}
