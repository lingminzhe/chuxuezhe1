package com.grgbanking.counter.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.grgbanking.counter.common.security.component.GrgAuth2ExceptionSerializer;

/**
 * @date 2021/7/8
 */
@JsonSerialize(using = GrgAuth2ExceptionSerializer.class)
public class InvalidException extends GrgAuth2Exception {

	public InvalidException(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "invalid_exception";
	}

	@Override
	public int getHttpErrorCode() {
		return 426;
	}

}
