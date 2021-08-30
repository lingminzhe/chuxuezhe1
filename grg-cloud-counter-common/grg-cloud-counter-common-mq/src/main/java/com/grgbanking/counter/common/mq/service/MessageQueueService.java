//package com.grgbanking.counter.common.mq.service;
//
//import com.grgbanking.counter.common.mq.dto.MessageDataDto;
//import org.apache.rocketmq.client.producer.SendCallback;
//import org.apache.rocketmq.client.producer.SendResult;
//
///**
// * RocketMQ消息发送Service
// * @Author:mcyang
// * @Date:2021/2/25 4:29 下午
// */
//public interface MessageQueueService {
//
//    /**
//     * 同步发送
//     * @param messageSendDto
//     */
//    SendResult syncSend(String topic, MessageDataDto messageSendDto);
//
//    /**
//     * 异步发送
//     * @param messageSendDto
//     */
//    void asyncSend(String topic, MessageDataDto messageSendDto, SendCallback sendCallback);
//}
