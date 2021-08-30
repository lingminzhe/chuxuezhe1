package com.grgbanking.counter.common.log.aspect;

import com.grgbanking.counter.common.log.event.SysLogEvent;
import com.grgbanking.counter.iam.api.dubbo.LogRemoteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

/**
 * @author lengleng 异步监听日志事件
 */
@Slf4j
@AllArgsConstructor
public class SysLogListener {

	private final LogRemoteService logRemoteService;

	@Async
	@Order
	@EventListener(SysLogEvent.class)
	public void saveSysLog(SysLogEvent event) {
		log.info("监听到操作日志：{}",event.getSysLog().toString());
		logRemoteService.save(event.getSysLog());
	}

}
