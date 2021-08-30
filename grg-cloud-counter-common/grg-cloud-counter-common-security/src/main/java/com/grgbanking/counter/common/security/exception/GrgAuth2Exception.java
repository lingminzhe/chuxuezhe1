package com.grgbanking.counter.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.grgbanking.counter.common.security.component.GrgAuth2ExceptionSerializer;
import lombok.Getter;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @date 2021/7/8 自定义OAuth2Exception
 */
@JsonSerialize(using = GrgAuth2ExceptionSerializer.class)
public class GrgAuth2Exception extends OAuth2Exception {

	@Getter
	private String errorCode;

	public GrgAuth2Exception(String msg) {
		super(msg);
	}

	public GrgAuth2Exception(String msg, Throwable t) {
		super(msg, t);
	}

	public GrgAuth2Exception(String msg, String errorCode) {
		super(msg);
		this.errorCode = errorCode;
	}

}
