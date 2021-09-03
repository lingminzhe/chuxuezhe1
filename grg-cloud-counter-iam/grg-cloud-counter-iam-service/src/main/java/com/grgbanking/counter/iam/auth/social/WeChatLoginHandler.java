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
@Component("WX")
@AllArgsConstructor
public class WeChatLoginHandler extends AbstractLoginHandler {

	private final SysUserService sysUserService;

//	private final SysSocialDetailsMapper sysSocialDetailsMapper;

	/**
	 * 微信登录传入code
	 * <p>
	 * 通过code 调用qq 获取唯一标识
	 * @param code
	 * @return
	 */
	@Override
	public String identify(String code) {
//		SysSocialDetails condition = new SysSocialDetails();
//		condition.setType(LoginTypeEnum.WECHAT.getType());
//		SysSocialDetails socialDetails = sysSocialDetailsMapper.selectOne(new QueryWrapper<>(condition));
//
//		String url = String.format(SecurityConstants.WX_AUTHORIZATION_CODE_URL, socialDetails.getAppId(),
//				socialDetails.getAppSecret(), code);
//		String result = HttpUtil.get(url);
//		log.debug("微信响应报文:{}", result);
//
//		Object obj = JSONUtil.parseObj(result).get("openid");
//		return obj.toString();
		return null;
	}

	/**
	 * openId 获取用户信息
	 * @param openId
	 * @return
	 */
	@Override
	public UserInfo info(String openId) {
//		SysUser user = sysUserService.getOne(Wrappers.<SysUser>query().lambda().eq(SysUser::getWxOpenid, openId));
//
//		if (user == null) {
//			log.info("微信未绑定:{}", openId);
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
//		List<SysUser> userList = sysUserService
//				.list(Wrappers.<SysUser>query().lambda().eq(SysUser::getWxOpenid, identify));
//
//		// 先把原有绑定关系去除,设置绑定为NULL
//		if (CollUtil.isNotEmpty(userList)) {
//			SysUser condition = new SysUser();
//			condition.setWxOpenid(identify);
//			sysUserService.update(condition, Wrappers.<SysUser>lambdaUpdate().set(SysUser::getWxOpenid, null));
//			log.info("微信账号 {} 更换账号绑定", identify);
//		}
//
//		user.setWxOpenid(identify);
//		sysUserService.updateById(user);
		return null;
	}

}
