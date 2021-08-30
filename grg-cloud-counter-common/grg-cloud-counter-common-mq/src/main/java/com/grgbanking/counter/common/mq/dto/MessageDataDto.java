package com.grgbanking.counter.common.mq.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 消息队列数据流转通讯的实体
 *
 * @Author:mcyang
 * @Date:2021/2/25 4:13 下午
 */
@Data
@Accessors(chain = true)
public class MessageDataDto<T> {

    @NotNull(message = "head不能为空")
    private MessageHeadDto head;

    private T body;

    private MessageDataDto() {

    }

    private MessageDataDto(MessageHeadDto head) {
        this.head = head;
    }

    private MessageDataDto(MessageHeadDto head, T body) {
        this.head = head;
        this.body = body;
    }

    public static MessageDataDto getInstance(@Valid MessageHeadDto head) {
        MessageDataDto messageDto = new MessageDataDto(head);
        return messageDto;
    }

    public static <T> MessageDataDto<T> getInstance(@Valid MessageHeadDto head, T body) {
        MessageDataDto messageDto = new MessageDataDto(head, body);
        return messageDto;
    }

}
