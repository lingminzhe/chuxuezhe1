package com.grgbanking.counter.common.socket.lineup.constant;

/**
 * 定义排队系统的队列key
 */
public class LineupConstants {

    /**
     * 用户视频呼叫排队的队列key
     */
    public static final String CUSTOMER_VIDEO_QUEUE_KEY = "grg:counter:queue:customer";

    /**
     * 在线坐席redis的key，hash数据结构，value是服务的用户id
     */
    public static final String EMPLOYEE_ONLINE_VIDEO_KEY = "grg:csr:online:video";
}
