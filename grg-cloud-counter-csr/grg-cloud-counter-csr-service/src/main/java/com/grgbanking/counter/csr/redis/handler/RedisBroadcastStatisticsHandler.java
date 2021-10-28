package com.grgbanking.counter.csr.redis.handler;

import com.corundumstudio.socketio.SocketIOClient;
import com.grgbanking.counter.common.core.util.SocketParam;
import com.grgbanking.counter.common.socket.lineup.service.LineupService;
import com.grgbanking.counter.common.socket.socket.constant.SocketApiNoConstants;
import com.grgbanking.counter.common.socket.socket.server.SocketServer;
import com.grgbanking.counter.common.socket.socket.service.SocketAbstractService;
import com.grgbanking.counter.csr.entity.GrgEmployeeServiceEntity;
import com.grgbanking.counter.csr.service.GrgBusiOptService;
import com.grgbanking.counter.csr.vo.BusiOptNumVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class RedisBroadcastStatisticsHandler extends RedisBroadcastAbstractHandler {

    @Autowired
    private LineupService lineupService;

    @Autowired
    private GrgBusiOptService grgBusiOptService;

    @Override
    public String setApiNo() {
        return SocketApiNoConstants.BUSI_OPT;
    }

    /**
     * 给所有坐席更新排队以及业务量数据
     * @param channel
     * @param param
     */
    @Override
    public void onMessage(String channel, SocketParam param) {
        Map<String, SocketIOClient> clientMap = SocketServer.getClientMap();
        log.info("通知所有坐席，客户排队，坐席业务量等!");
        clientMap.forEach((employeeId, client) -> {
            //当前排队用户数量
            Long waitingQueue = lineupService.rank();
            GrgEmployeeServiceEntity entity = new GrgEmployeeServiceEntity();
            entity.setEmployeeId(employeeId);
            BusiOptNumVo buisOptNum = grgBusiOptService.getBuisOptNum(entity);
            buisOptNum.setQueueNum(String.valueOf(waitingQueue));
            param.setBody(param);
            client.sendEvent(SocketAbstractService.PUSH_EVENT_NAME, param);
        });

    }
}
