package com.grgbanking.counter.common.log.event;

import com.grgbanking.counter.common.log.bo.SysLogBo;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author grgbanking 系统日志事件
 */
@Getter
@AllArgsConstructor
public class SysLogEvent {

	private final SysLogBo sysLog;

}
