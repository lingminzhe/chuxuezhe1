//package com.grgbanking.counter.common.mq.service.impl;
//
//import com.grgbanking.counter.common.mq.dto.MessageDataDto;
//import com.grgbanking.counter.common.mq.service.MessageQueueService;
//import org.apache.rocketmq.client.producer.SendCallback;
//import org.apache.rocketmq.client.producer.SendResult;
//import org.apache.rocketmq.spring.core.RocketMQTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.support.MessageBuilder;
//import org.springframework.stereotype.Service;
//import org.springframework.util.Assert;
//import org.springframework.validation.annotation.Validated;
//
//import javax.validation.Valid;
//import java.util.UUID;
//
///**
// * @Author:mcyang
// * @Date:2021/2/25 4:29 下午
// */
//@Valid
//@Validated
//@Service
//public class MessageQueueServiceImpl implements MessageQueueService {
//
//
//    @Autowired
//    private RocketMQTemplate rocketMQTemplate;
//
//    @Override
//    public SendResult syncSend(String topic, MessageDataDto messageDataDto) {
//        checkData(messageDataDto);
//        Message message = MessageBuilder.withPayload(messageDataDto).setHeader("KEYS", UUID.randomUUID().toString()).build();;
//        SendResult sendResult = rocketMQTemplate.syncSend(topic, message);
//        return sendResult;
//    }
//
//    @Override
//    public void asyncSend(String topic, MessageDataDto messageDataDto,SendCallback sendCallback) {
//        checkData(messageDataDto);
//        Message message = MessageBuilder.withPayload(messageDataDto).setHeader("KEYS", UUID.randomUUID().toString()).build();;
//        rocketMQTemplate.asyncSend(topic, message, sendCallback);
//    }
//
//    private void checkData(MessageDataDto messageDataDto) {
//        Assert.notNull(messageDataDto,"error send message,message is NULL");
//        Assert.notNull(messageDataDto.getHead(),"error send message,head is NULL");
//        Assert.notNull(messageDataDto.getHead().getTermId(),"error send message,head is NULL");
//        Assert.notNull(messageDataDto.getHead().getChannel(),"error send message,head is NULL");
//        Assert.notNull(messageDataDto.getHead().getOperateType(),"error send message,head is NULL");
//    }
//
//}
