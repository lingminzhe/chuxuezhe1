package com.grgbanking.counter.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.grgbanking.counter.common.security.component.GrgAuth2ExceptionSerializer;
import org.springframework.http.HttpStatus;

/**
 * @date 2021/7/8
 */
@JsonSerialize(using = GrgAuth2ExceptionSerializer.class)
public class UnauthorizedException extends GrgAuth2Exception {

	public UnauthorizedException(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "unauthorized";
	}

	@Override
	public int getHttpErrorCode() {
		return HttpStatus.UNAUTHORIZED.value();
	}

}
