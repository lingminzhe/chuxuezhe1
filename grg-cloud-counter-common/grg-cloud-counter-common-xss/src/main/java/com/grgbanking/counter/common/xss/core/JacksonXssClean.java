package com.grgbanking.counter.common.xss.core;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.grgbanking.counter.common.xss.config.XssProperties;
import com.grgbanking.counter.common.xss.utils.XssUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * jackson xss 处理
 *
 */
@Slf4j
@RequiredArgsConstructor
public class JacksonXssClean extends JsonDeserializer<String> {

	private final XssProperties properties;

	private final XssCleaner xssCleaner;

	@Override
	public String deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
		// XSS filter
		String text = p.getValueAsString();
		if (text == null) {
			return null;
		}
		if (XssHolder.isEnabled()) {
			String value = xssCleaner.clean(XssUtil.trim(text, properties.isTrimText()));
			log.debug("Json property value:{} cleaned up by mica-xss, current value is:{}.", text, value);
			return value;
		}
		else {
			return XssUtil.trim(text, properties.isTrimText());
		}
	}

}
