package com.grgbanking.counter.iam.auth.service;


import com.grgbanking.counter.common.core.util.Resp;

/**
 */
public interface MobileService {

	/**
	 * 发送手机验证码
	 * @param mobile mobile
	 * @return code
	 */
	Resp sendSmsCode(String mobile);

}
