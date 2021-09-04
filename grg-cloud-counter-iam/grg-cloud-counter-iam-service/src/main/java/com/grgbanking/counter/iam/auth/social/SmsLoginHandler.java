package com.grgbanking.counter.iam.auth.social;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.grgbanking.counter.common.core.constant.enums.LoginTypeEnum;
import com.grgbanking.counter.iam.api.dto.UserInfo;
import com.grgbanking.counter.iam.api.entity.SysSocialAuthUserEntity;
import com.grgbanking.counter.iam.api.entity.SysUserEntity;
import com.grgbanking.counter.iam.service.SysSocialAuthUserService;
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

	private final SysSocialAuthUserService sysSocialAuthUserService;

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
		SysSocialAuthUserEntity socialAuthUser = sysSocialAuthUserService.getOne(Wrappers.<SysSocialAuthUserEntity>query().lambda().eq(SysSocialAuthUserEntity::getLoginNo, identify).eq(SysSocialAuthUserEntity::getLoginType, LoginTypeEnum.SMS.getType()));
		if (socialAuthUser == null) {
			log.error("手机号未注册：{}", identify);
			return null;
		}
		UserInfo userInfo = new UserInfo();
		SysUserEntity user = new SysUserEntity();
		user.setUserId(socialAuthUser.getId());
		user.setUserName(socialAuthUser.getLoginNo());
		user.setNickName(socialAuthUser.getUserName());
		user.setEnabled(socialAuthUser.getEnabled());
		user.setAvatar(socialAuthUser.getAvatar());
		user.setPhone(socialAuthUser.getLoginNo());
		userInfo.setSysUser(user);

		return userInfo;
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
