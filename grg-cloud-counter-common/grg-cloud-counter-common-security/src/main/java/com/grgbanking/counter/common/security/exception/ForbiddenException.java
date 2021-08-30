package com.grgbanking.counter.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.grgbanking.counter.common.security.component.GrgAuth2ExceptionSerializer;
import org.springframework.http.HttpStatus;

/**
 * @date 2021/7/8
 */
@JsonSerialize(using = GrgAuth2ExceptionSerializer.class)
public class ForbiddenException extends GrgAuth2Exception {

	public ForbiddenException(String msg) {
		super(msg);
	}

	public ForbiddenException(String msg, Throwable t) {
		super(msg, t);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "access_denied";
	}

	@Override
	public int getHttpErrorCode() {
		return HttpStatus.FORBIDDEN.value();
	}

}
