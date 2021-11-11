package com.grgbanking.counter.bank.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.grgbanking.counter.bank.api.SmsApi;
import com.grgbanking.counter.bank.api.dubbo.RemoteMobileService;
import com.grgbanking.counter.bank.api.vo.MobileSmsVo;
import com.grgbanking.counter.common.core.constant.CacheConstants;
import com.grgbanking.counter.common.core.constant.SecurityConstants;
import com.grgbanking.counter.common.core.constant.enums.LoginTypeEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 手机登录相关业务实现
 * @author grgbanking
 */
@Slf4j
@DubboService
@AllArgsConstructor
public class MobileServiceImpl implements RemoteMobileService {

	@Autowired
	private SmsApi smsApi;

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
			log.info("手机号验证码未过期！");
			return false;
		}
		//验证码
		String code = RandomUtil.randomNumbers(Integer.parseInt(SecurityConstants.CODE_SIZE));
		//短信文本
		String smsText = "短信验证码：" + code + " , 5分钟内有效";
		log.debug("手机号生成验证码成功！" );
//		System.out.println(CacheConstants.DEFAULT_CODE_KEY + LoginTypeEnum.SMS.getType() + StringPool.AT + mobile);
		//调用短信平台接口
		try {
			//短信接口
			smsApi.getSmsApi(mobile,smsText);
		}catch (IOException e){
			e.printStackTrace();
		}
		//短信发送成功后再将短信验证码存入
		redisTemplate.opsForValue().set(CacheConstants.DEFAULT_CODE_KEY + LoginTypeEnum.SMS.getType() + StringPool.AT + mobile, code, SecurityConstants.CODE_TIME, TimeUnit.SECONDS);

		return true;
	}

	@Override
	public boolean verifySmsCode(MobileSmsVo mobile) {
		Object codeObj = redisTemplate.opsForValue().get(CacheConstants.DEFAULT_CODE_KEY + LoginTypeEnum.SMS.getType() + StringPool.AT + mobile.getMobile());
		if (codeObj != null) {
			//短信平台正常使用时
			if (mobile.getMobile()!=null && mobile.getCode().equals(codeObj) ) {
				return true;
			}
			//短信平台使用不了  用假数据
//			return "1234".equals(mobile.getCode());
		}
		return false;
	}

	@Override
	public boolean sendBusiSms(String mobile, String name) {
		//
		String s = DateUtil.date().toStringDefaultTimeZone();
		String smsText = "尊敬的客户，您于"+s+"办理的"+name+"业务已办理完成！";
		//获取办理的业务类型
		try {
			smsApi.getSmsApi(mobile,smsText);
		}catch (IOException e){
			e.printStackTrace();
		}
		return true;
	}

}
