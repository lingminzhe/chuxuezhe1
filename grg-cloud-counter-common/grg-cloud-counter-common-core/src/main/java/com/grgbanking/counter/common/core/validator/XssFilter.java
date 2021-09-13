/**
 * Copyright (c)2021 GRGBanking All rights reserved.
 *
 * https://www.grgbanking.com
 *
 * 版权所有，侵权必究！
 */

package com.grgbanking.counter.common.core.validator;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * XSS过滤
 *
 * @author MARK xx@grgbanking.com
 */
public class XssFilter implements Filter {

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
		XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper(
				(HttpServletRequest) request);
		chain.doFilter(xssRequest, response);
	}

	@Override
	public void destroy() {
	}

}