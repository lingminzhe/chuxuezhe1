package com.grgbanking.counter.iam.auth.social;

import com.grgbanking.counter.iam.api.dto.UserInfo;
import com.grgbanking.counter.iam.api.entity.SysSocialAuthUserEntity;
import com.grgbanking.counter.iam.service.SysUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 */
@Slf4j
@Component("SMS")
@AllArgsConstructor
public class SmsLoginHandler extends AbstractLoginHandler {

	private final SysUserService sysUserService;

	/**
	 * 验证码登录传入为手机号 不用不处理
	 * @param mobile
	 * @return
	 */
	@Override
	public String identify(String mobile) {
		return mobile;
	}

	/**
	 * 通过mobile 获取用户信息
	 * @param identify
	 * @return
	 */
	@Override
	public UserInfo info(String identify) {
//		SysUser user = sysUserService.getOne(Wrappers.<SysUser>query().lambda().eq(SysUser::getPhone, identify));
//
//		if (user == null) {
//			log.info("手机号未注册:{}", identify);
//			return null;
//		}
//		return sysUserService.findUserInfo(user);
		return null;
	}

	/**
	 * 绑定逻辑
	 * @param user 用户实体
	 * @param identify 渠道返回唯一标识
	 * @return
	 */
	@Override
	public Boolean bind(SysSocialAuthUserEntity user, String identify) {
//		user.setGiteeLogin(identify);
//		sysUserService.updateById(user);
		return null;
	}

}
