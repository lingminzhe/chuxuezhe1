package com.grgbanking.counter.common.core.util;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.grgbanking.counter.common.core.exception.CheckedException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * WEB工具类
 *
 * Miscellaneous utilities for web applications.
 *
 * @author grgbanking
 */
@Slf4j
@UtilityClass
public class WebUtils extends org.springframework.web.util.WebUtils {

	private final String BASIC_ = "Basic ";

	private final String UNKNOWN = "unknown";

	/**
	 * 判断是否ajax请求 spring ajax 返回含有 ResponseBody 或者 RestController注解
	 * @param handlerMethod HandlerMethod
	 * @return 是否ajax请求
	 */
	public boolean isBody(HandlerMethod handlerMethod) {
		ResponseBody responseBody = ClassUtils.getAnnotation(handlerMethod, ResponseBody.class);
		return responseBody != null;
	}

	/**
	 * 获取 HttpServletRequest
	 * @return {HttpServletRequest}
	 */
	public HttpServletRequest getRequest() {
		try {
			RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
			return ((ServletRequestAttributes) requestAttributes).getRequest();
		}
		catch (IllegalStateException e) {
			return null;
		}
	}

	/**
	 * 获取 HttpServletResponse
	 * @return {HttpServletResponse}
	 */
	public HttpServletResponse getResponse() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
	}

	public static String getOrigin(){
		HttpServletRequest request = getRequest();
		return request.getHeader("Origin");
	}

	/**
	 * 返回json
	 * @param response HttpServletResponse
	 * @param result 结果对象
	 */
	public void renderJson(HttpServletResponse response, Object result) {
		renderJson(response, result, MediaType.APPLICATION_JSON_VALUE);
	}

	/**
	 * 返回json
	 * @param response HttpServletResponse
	 * @param result 结果对象
	 * @param contentType contentType
	 */
	public void renderJson(HttpServletResponse response, Object result, String contentType) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType(contentType);
		try (PrintWriter out = response.getWriter()) {
			out.append(JSONUtil.toJsonStr(result));
		}
		catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 获取ip
	 * @return {String}
	 */
	public String getIP() {
		return getIP(WebUtils.getRequest());
	}

	/**
	 * 获取ip
	 * @param request HttpServletRequest
	 * @return {String}
	 */
	public String getIP(HttpServletRequest request) {
		Assert.notNull(request, "HttpServletRequest is null");
		String ip = request.getHeader("X-Requested-For");
		if (StrUtil.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Forwarded-For");
		}
		if (StrUtil.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (StrUtil.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StrUtil.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (StrUtil.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (StrUtil.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return StrUtil.isBlank(ip) ? null : ip.split(",")[0];
	}

	/**
	 * 解析 client id
	 * @param header
	 * @param defVal
	 * @return 如果解析失败返回默认值
	 */
	public String extractClientId(String header, final String defVal) {

		if (header == null || !header.startsWith(BASIC_)) {
			log.debug("请求头中client信息为空: {}", header);
			return defVal;
		}
		byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
		byte[] decoded;
		try {
			decoded = Base64.decode(base64Token);
		}
		catch (IllegalArgumentException e) {
			log.debug("Failed to decode basic authentication token: {}", header);
			return defVal;
		}

		String token = new String(decoded, StandardCharsets.UTF_8);

		int delim = token.indexOf(":");

		if (delim == -1) {
			log.debug("Invalid basic authentication token: {}", header);
			return defVal;
		}
		return token.substring(0, delim);
	}

	/**
	 * 从请求头中解析 client id
	 * @param header
	 * @return
	 */
	public Optional<String> extractClientId(String header) {
		return Optional.ofNullable(extractClientId(header, null));
	}

	/**
	 * 从request 获取CLIENT_ID
	 * @return
	 */
	public String getClientId(String header) {
		String clientId = extractClientId(header, null);
		if (clientId == null) {
			throw new CheckedException("Invalid basic authentication token");
		}
		return clientId;
	}

	/**
	 * 检查主机是否可达
	 * @param host
	 * @param timeOut
	 * @return
	 */
	public static boolean isHostReachable(String host, Integer timeOut) {
		try {
			return InetAddress.getByName(host).isReachable(timeOut);
		} catch (UnknownHostException e) {
			log.error(e.getMessage(),e);
		} catch (IOException e) {
			log.error(e.getMessage(),e);
		}
		return false;
	}

	/**
	 * 检查IP是否合法
	 * @param ip
	 * @return
	 */
	public static boolean checkIP(String ip){
		String ipReg = "^(([1-9]|([1-9]\\d)|(1\\d\\d)|(2([0-4]\\d|5[0-5])))\\.)(([1-9]|([1-9]\\d)|(1\\d\\d)|(2([0-4]\\d|5[0-5])))\\.){2}([1-9]|([1-9]\\d)|(1\\d\\d)|(2([0-4]\\d|5[0-5])))$";
		Pattern ipPattern = Pattern.compile(ipReg);
		boolean flag = ipPattern.matcher(ip).matches();
		return flag;
	}

}
