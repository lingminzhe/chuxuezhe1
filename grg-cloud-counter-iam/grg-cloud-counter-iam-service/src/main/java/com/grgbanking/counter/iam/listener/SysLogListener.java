package com.grgbanking.counter.iam.listener;

import com.grgbanking.counter.common.log.bo.SysLogBo;
import com.grgbanking.counter.common.log.event.SysLogEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 如某个模块需要添加日志，则需把此文件拷贝过去
 * @Author:mcyang
 * @Date:2021/5/13 上午9:46
 */
@Component
public class SysLogListener {

//	@Autowired
//    private RemoteSysLogInnerService remoteSysLogService;

	@Async
	@Order
	@EventListener(SysLogEvent.class)
	public void saveSysLog(SysLogEvent event) {
		SysLogBo sysLog = event.getSysLog();
//		remoteSysLogService.save(sysLog);
	}

}
