package com.grgbanking.counter.gateway.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grgbanking.counter.common.core.util.Resp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * <p>
 * 网关异常通用处理器，只作用在webflux 环境下 , 优先级低于 {@link ResponseStatusExceptionHandler} 执行
 */
@Slf4j
@Order(-1)
@Configuration
@RequiredArgsConstructor
public class GlobalExceptionConfiguration implements ErrorWebExceptionHandler {

	private final ObjectMapper objectMapper;

	@Override
	public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
		log.error("报错：{}",exchange.getRequest().getPath(),ex);
		ServerHttpResponse response = exchange.getResponse();

		if (response.isCommitted()) {
			return Mono.error(ex);
		}

		// header set
		response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
		if (ex instanceof ResponseStatusException) {
			response.setStatusCode(((ResponseStatusException) ex).getStatus());
		}

		return response.writeWith(Mono.fromSupplier(() -> {
			DataBufferFactory bufferFactory = response.bufferFactory();
			try {
				return bufferFactory.wrap(objectMapper.writeValueAsBytes(Resp.failed(ex.getMessage())));
			} catch (JsonProcessingException e) {
				log.warn("Error writing response", ex);
				return bufferFactory.wrap(new byte[0]);
			}
		}));
	}

}
