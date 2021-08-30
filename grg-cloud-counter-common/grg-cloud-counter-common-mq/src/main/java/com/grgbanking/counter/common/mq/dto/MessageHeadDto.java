package com.grgbanking.counter.common.mq.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;


/**
 * 消息队列数据流转通讯的Head实体
 * @Author:mcyang
 * @Date:2021/2/25 4:14 下午
 */
@Data
@Accessors(chain = true)
public class MessageHeadDto {

    private static final String successCode = "0000";
    private static final String successMsg = "success";

    /**
     * 终端ID
     */
    @NotBlank(message = "终端设备ID不能为空")
    private String termId;

    /**
     * 操作类型
     */
    @NotBlank(message = "操作类型不能为空")
    private String operateType;

    /**
     * 操作流水号
     */
    private String operateNo;

    /**
     * 渠道
     */
    @NotBlank(message = "渠道不能为空")
    private String channel;

    /**
     * 状态码
     */
    private String code = successCode;

    /**
     * 请求主体
     */
    private String reqObjId;

    /**
     * 请求类型
     */
    private String reqObjType;

    /**
     * 状态信息
     */
    private String msg = successMsg;

    private MessageHeadDto() {

    }

    /**
     *
     * @param termId        终端设备ID
     * @param operateType   操作类型
     * @param channel       渠道
     * @return
     */
    public static MessageHeadDto getInstance(String termId, String operateType, String channel) {
        MessageHeadDto headDto = new MessageHeadDto(termId,operateType,channel);
        return headDto;
    }

    /**
     *
     * @param termId        终端设备ID
     * @param operateType   操作类型
     * @param operateNo     操作流水号
     * @param channel       渠道
     * @return
     */
    public static MessageHeadDto getInstance(String termId, String operateType, String operateNo, String channel) {
        MessageHeadDto headDto = new MessageHeadDto(termId,operateType,operateNo,channel);
        return headDto;
    }

    /**
     *
     * @param termId        终端设备ID
     * @param operateType   操作类型
     * @param operateNo     操作流水号
     * @param channel       渠道
     * @param code          响应状态码
     * @param msg           响应描述
     * @return
     */
    public static MessageHeadDto getInstance(String termId, String operateType, String operateNo, String channel,String code, String msg) {
        MessageHeadDto headDto = new MessageHeadDto(termId,operateType,operateNo,channel,code,msg);
        return headDto;
    }

    private MessageHeadDto(String termId, String operateType, String channel) {
        this.termId = termId;
        this.operateType = operateType;
        this.channel = channel;
    }

    private MessageHeadDto(String termId, String operateType, String operateNo, String channel) {
        this.termId = termId;
        this.operateType = operateType;
        this.operateNo = operateNo;
        this.channel = channel;
    }

    private MessageHeadDto(String termId, String operateType, String operateNo, String channel, String code, String msg) {
        this.termId = termId;
        this.operateType = operateType;
        this.operateNo = operateNo;
        this.channel = channel;
        this.code = code;
        this.msg = msg;
    }

}
