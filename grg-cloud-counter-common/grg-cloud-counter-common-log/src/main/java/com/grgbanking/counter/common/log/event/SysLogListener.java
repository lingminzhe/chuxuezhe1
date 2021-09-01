
package com.grgbanking.counter.common.log.event;
import com.grgbanking.counter.iam.api.dto.SysLogDTO;
import com.grgbanking.counter.iam.api.dubbo.RemoteLogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

/**
 * @author 异步监听日志事件
 */
@Slf4j
@AllArgsConstructor
public class SysLogListener {

	private final RemoteLogService remoteLogService;

	@Async
	@Order
	@EventListener(SysLogEvent.class)
	public void saveSysLog(SysLogEvent event) {
		SysLogDTO sysLog = event.getSysLog();
		remoteLogService.save(sysLog);
	}

}
