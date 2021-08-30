package com.grgbanking.counter.common.security.component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.grgbanking.counter.common.core.constant.CommonConstants;
import com.grgbanking.counter.common.security.exception.GrgAuth2Exception;
import lombok.SneakyThrows;

/**
 * @date 2021/11/16
 * OAuth2 异常格式化
 */
public class GrgAuth2ExceptionSerializer extends StdSerializer<GrgAuth2Exception> {

	public GrgAuth2ExceptionSerializer() {
		super(GrgAuth2Exception.class);
	}

	@Override
	@SneakyThrows
	public void serialize(GrgAuth2Exception value, JsonGenerator gen, SerializerProvider provider) {
		gen.writeStartObject();
		gen.writeObjectField("code", CommonConstants.FAIL);
		gen.writeStringField("msg", value.getMessage());
		gen.writeStringField("data", value.getErrorCode());
		gen.writeEndObject();
	}

}
