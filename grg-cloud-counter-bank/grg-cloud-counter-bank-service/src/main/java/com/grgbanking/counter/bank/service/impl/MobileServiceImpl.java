package com.grgbanking.counter.bank.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.grgbanking.counter.bank.service.MobileService;
import com.grgbanking.counter.common.core.constant.CacheConstants;
import com.grgbanking.counter.common.core.constant.SecurityConstants;
import com.grgbanking.counter.common.core.constant.enums.LoginTypeEnum;
import com.grgbanking.counter.common.core.util.Resp;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 手机登录相关业务实现
 */
@Slf4j
@Service
@AllArgsConstructor
public class MobileServiceImpl implements MobileService {

	private final RedisTemplate redisTemplate;

	/**
	 * 发送手机验证码 TODO: 调用短信网关发送验证码,测试返回前端
	 * @param mobile mobile
	 * @return code
	 */
	@Override
	public Resp sendSmsCode(String mobile) {
		Object codeObj = redisTemplate.opsForValue().get(CacheConstants.DEFAULT_CODE_KEY + LoginTypeEnum.SMS.getType() + StringPool.AT + mobile);
		if (codeObj != null) {
			log.info("手机号验证码未过期:{}，{}", mobile, codeObj);
			return Resp.success(Boolean.FALSE, "验证码发送过频繁");
		}

		String code = RandomUtil.randomNumbers(Integer.parseInt(SecurityConstants.CODE_SIZE));
		log.debug("手机号生成验证码成功:{},{}", mobile, code);
		redisTemplate.opsForValue().set(CacheConstants.DEFAULT_CODE_KEY + LoginTypeEnum.SMS.getType() + StringPool.AT + mobile, code, SecurityConstants.CODE_TIME, TimeUnit.SECONDS);
		Map<String, Object> result = new HashMap<>();
		result.put("code", code);
		return Resp.success(result);
	}

}
