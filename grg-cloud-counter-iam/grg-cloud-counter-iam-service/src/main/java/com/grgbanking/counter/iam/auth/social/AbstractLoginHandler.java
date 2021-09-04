package com.grgbanking.counter.iam.auth.social;


import com.grgbanking.counter.iam.api.dto.UserInfo;

/**
 */
public abstract class AbstractLoginHandler implements LoginHandler {

	/**
	 * 处理方法
	 * @param code
	 * @return
	 */
	@Override
	public UserInfo handle(String code) {
		String identify = identify(code);
		return info(identify);
	}

}
