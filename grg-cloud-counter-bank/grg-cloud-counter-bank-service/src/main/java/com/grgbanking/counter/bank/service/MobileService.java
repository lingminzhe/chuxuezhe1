package com.grgbanking.counter.bank.service;


import com.grgbanking.counter.bank.api.vo.MobileSmsVo;
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

	Resp verifySmsCode(MobileSmsVo mobile);
}
