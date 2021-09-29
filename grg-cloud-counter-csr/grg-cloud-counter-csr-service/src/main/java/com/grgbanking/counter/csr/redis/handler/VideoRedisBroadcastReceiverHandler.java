//package com.grgbanking.counter.csr.redis.handler;
//
//import com.grgbanking.counter.common.core.util.SocketParam;
//import com.grgbanking.counter.common.core.util.SocketParamHead;
//import com.grgbanking.counter.common.socket.socket.constant.SocketApiNoConstants;
//import com.grgbanking.counter.common.socket.socket.entity.EmployeeService;
//import com.grgbanking.counter.common.socket.socket.service.SocketAbstractService;
//import com.grgbanking.counter.csr.service.TencentService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// * 视频呼叫处理handler
// */
//@Slf4j
//@Component
//public class VideoRedisBroadcastReceiverHandler extends RedisBroadcastAbstractHandler {
//
//    @Autowired
//    private SocketAbstractService socketAbstractService;
//
//    @Autowired
//    private TencentService tencentService;
//
//    @Override
//    public String setApiNo() {
//        return SocketApiNoConstants.VIDEO_CMD;
//    }
//
//    @Override
//    public void onMessage(String channel, SocketParam param) {
//        SocketParamHead head = param.getHead();
//        String clientId = null;
//        if (head.getApiNo().equals(SocketApiNoConstants.VIDEO_CMD)){
//            EmployeeService employeeService = (EmployeeService)param.getBody();
//            String employeeId = employeeService.getEmployeeId();
//            clientId = employeeId;
//            employeeService.setUserSig(tencentService.getUserSig(employeeId));
//        }else {
//            clientId = String.valueOf(param.getBody());
//        }
//        /**
//         * 给指定坐席发送socket消息通知有用户进行视频申请
//         */
//        System.out.println("报文内容: {}" + param);
//        log.info("发送视频呼叫通知坐席：{}",clientId);
//        socketAbstractService.sendMessage(clientId,param);
//    }
//
//}
