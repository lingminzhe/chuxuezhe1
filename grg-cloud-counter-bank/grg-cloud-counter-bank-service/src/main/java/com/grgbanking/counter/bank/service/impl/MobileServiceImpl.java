package com.grgbanking.counter.bank.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.grgbanking.counter.bank.api.dubbo.RemoteMobileService;
import com.grgbanking.counter.bank.api.vo.MobileSmsVo;
import com.grgbanking.counter.common.core.constant.CacheConstants;
import com.grgbanking.counter.common.core.constant.SecurityConstants;
import com.grgbanking.counter.common.core.constant.enums.LoginTypeEnum;
import com.grgbanking.counter.common.core.encrypt.MD5Util;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 手机登录相关业务实现
 * @author grgbanking
 */
@Slf4j
@DubboService
@AllArgsConstructor
public class MobileServiceImpl implements RemoteMobileService {


	private final RedisTemplate redisTemplate;

	/**
	 * 发送手机验证码
	 * @param mobile mobile
	 * @return code
	 */
	@Override
	public boolean sendSmsCode(String mobile) {
		String key = CacheConstants.DEFAULT_CODE_KEY + LoginTypeEnum.SMS.getType() + StringPool.AT + mobile;
		Object codeObj = redisTemplate.opsForValue().get(key);
		if (codeObj != null) {
			//TODO 过期时间查询
			Long expire = redisTemplate.opsForValue().getOperations().getExpire(key, TimeUnit.SECONDS);
			log.info("手机号验证码未过期:{}，{}", mobile, codeObj);
			return false;
		}
		//验证码
		String code = RandomUtil.randomNumbers(Integer.parseInt(SecurityConstants.CODE_SIZE));
		//短信文本
		String smsText = "短信验证码：" + code;
		log.info("手机号生成验证码成功:{}",  code);
		redisTemplate.opsForValue().set(CacheConstants.DEFAULT_CODE_KEY + LoginTypeEnum.SMS.getType() + StringPool.AT + mobile, code, SecurityConstants.CODE_TIME, TimeUnit.SECONDS);
		System.out.println(CacheConstants.DEFAULT_CODE_KEY + LoginTypeEnum.SMS.getType() + StringPool.AT + mobile);
		//调用短信平台接口
		try {
			getSmsApi(mobile,smsText);
		}catch (IOException e){
			e.printStackTrace();
		}
		Map<String, Object> result = new HashMap<>(8);
		result.put("code", code);
		return true;
	}

	@Override
	public boolean verifySmsCode(MobileSmsVo mobile) {
		Object codeObj = redisTemplate.opsForValue().get(CacheConstants.DEFAULT_CODE_KEY + LoginTypeEnum.SMS.getType() + StringPool.AT + mobile.getMobile());
		if (codeObj != null) {
			if (mobile.getMobile()!=null && mobile.getCode() .equals(codeObj) ) {
				return true;
			}
		}
		return false;
	}

	/**
	 *	 * 输入参数：	说明
	 * 	 * u	用户号码	用户手机号码，11位有效数字
	 * 	 * p	认证明文	随机16位字符串
	 * 	 * e	认证密文
	 * 	 * t	时间戳
	 * 	 * f	类型	固定传值（ycyh）
	 * 	 * d	发送时间	传空值表示立即发送
	 * 	 * c	发送内容
	 * @param mobile
	 * @throws IOException
	 */
	public void getSmsApi(String mobile,String code) throws IOException {
		String url = "http://oa.grgbanking.com/interface/sms/phone_msg.php";
		String f = "ycyh";
		//生成随机16位字符串
		String randomString = RandomUtil.randomString(16);
		long millis = System.currentTimeMillis();
		String currentTimeMillis = String.valueOf(millis);
		String md5String = MD5Util.getMD5String("YcGrgBank" + currentTimeMillis + "BaRnkm" + mobile + randomString);

		StringBuilder s1 = new StringBuilder();
		String s2 = s1.append("?u=" + mobile + "&p=" + randomString
				+ "&e=" + md5String) + "&t=" + currentTimeMillis + "&f=" + f
				 + "&c=" + code;
		String s3 = url+s2;

		String result2 = HttpRequest.get(s3)
				.header(Header.USER_AGENT, "//头信息，多个头信息多次调用此方法即可Hutool http")
				//表单内容
//				.form(s2)
				//超时，毫秒
				.timeout(20000)
				.execute().body();
		log.info(result2);
	}

}
