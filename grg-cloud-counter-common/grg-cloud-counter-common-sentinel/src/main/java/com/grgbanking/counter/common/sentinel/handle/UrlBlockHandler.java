package com.grgbanking.counter.common.sentinel.handle;

import cn.hutool.http.ContentType;
import cn.hutool.json.JSONUtil;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.grgbanking.counter.common.core.util.Resp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 降级/限流返回封装
 * @author calon
 */
@Slf4j
public class UrlBlockHandler implements BlockExceptionHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
		log.error("UrlBlockHandler，Sentinel Feign 降级。APP:{}，资源名称：{}", e.getRuleLimitApp(),e.getRule().getResource(), e);
		response.setCharacterEncoding("UTF-8");
		response.setContentType(ContentType.JSON.toString());
		response.getWriter().print(JSONUtil.toJsonStr(Resp.error(HttpStatus.TOO_MANY_REQUESTS.value(),"应用-触发系统保护")));
	}

}
