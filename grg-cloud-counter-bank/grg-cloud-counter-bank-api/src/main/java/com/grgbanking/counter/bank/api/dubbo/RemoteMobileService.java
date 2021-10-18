package com.grgbanking.counter.bank.api.dubbo;


import com.grgbanking.counter.bank.api.vo.MobileSmsVo;

/**
 */
public interface RemoteMobileService {


	/**
	 * 发送手机验证码
	 * @param mobile mobile
	 * @return code
	 */
	boolean sendSmsCode(String mobile);

	boolean verifySmsCode(MobileSmsVo mobile);
}
