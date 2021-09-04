package com.grgbanking.counter.iam.auth.social;


import com.grgbanking.counter.iam.api.dto.UserInfo;

/**
 */
public abstract class AbstractLoginHandler implements LoginHandler {

	/**
	 * 处理方法
	 * @param loginStr 登录参数
	 * @return
	 */
	@Override
	public UserInfo handle(String loginStr) {
		String identify = identify(loginStr);
		return info(identify);
	}

}
