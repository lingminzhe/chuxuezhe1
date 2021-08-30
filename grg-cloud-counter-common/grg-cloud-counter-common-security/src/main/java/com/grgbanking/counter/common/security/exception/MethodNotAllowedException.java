package com.grgbanking.counter.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.grgbanking.counter.common.security.component.GrgAuth2ExceptionSerializer;
import org.springframework.http.HttpStatus;

/**
 * @date 2021/7/8
 */
@JsonSerialize(using = GrgAuth2ExceptionSerializer.class)
public class MethodNotAllowedException extends GrgAuth2Exception {

	public MethodNotAllowedException(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "method_not_allowed";
	}

	@Override
	public int getHttpErrorCode() {
		return HttpStatus.METHOD_NOT_ALLOWED.value();
	}

}
