package com.grgbanking.counter.csr.socket;

import com.grgbanking.counter.common.core.util.SocketParam;
import com.grgbanking.counter.common.socket.broadcast.constant.RedisBroadcastConstants;
import com.grgbanking.counter.common.socket.broadcast.service.RedisBroadcastService;
import com.grgbanking.counter.common.socket.lineup.service.LineupService;
import com.grgbanking.counter.common.socket.socket.constant.SocketApiNoConstants;
import com.grgbanking.counter.common.socket.socket.service.SocketAbstractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SocketServiceImpl extends SocketAbstractService {

    @Autowired
    private LineupService lineupService;

    @Autowired
    private RedisBroadcastService redisBroadcastService;

    @Override
    public void connected(String clientId) {
        log.info("坐席上线:{}", clientId);
    }

    @Override
    public void disconnect(String clientId) {
        log.info("坐席终端端开连接:{}", clientId);
    }

    /**
     * 当无apiNo或者该apiNo无对应处理器时，消息将会传递到此函数
     *
     * @param clientId 消息来源的客户端Id
     * @param param    消息内容
     * @return
     */
    @Override
    public boolean onMessage(String clientId, SocketParam param) {
        String apiNo = param.getHead().getApi_no();
        if (apiNo.equals(SocketApiNoConstants.VIDEO_CMD)){
            param.getHead().setApi_no(SocketApiNoConstants.BUSI_NO);
            String customerId = lineupService.findCustomer(clientId);
            param.getHead().setMsg(customerId);
            redisBroadcastService.sendBroadcast(RedisBroadcastConstants.BROADCAST_CHANNEL_APP, param);
        }
        log.info("接收到默认无人处理的消息,ClientId:{},消息：{}", clientId, param);
        return false;
    }
}
