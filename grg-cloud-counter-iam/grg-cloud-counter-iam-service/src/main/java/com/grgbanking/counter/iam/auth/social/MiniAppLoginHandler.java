package com.grgbanking.counter.iam.auth.social;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.grgbanking.counter.iam.api.dto.UserInfo;
import com.grgbanking.counter.iam.api.entity.SysSocialAuthUserEntity;
import com.grgbanking.counter.iam.service.SysSocialAuthUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 微信小程序
 */
@Slf4j
@Component("MINI")
@AllArgsConstructor
public class MiniAppLoginHandler extends AbstractLoginHandler {

	private final SysSocialAuthUserService sysSocialAuthUserService;

//	private final SysSocialDetailsMapper sysSocialDetailsMapper;

	/**
	 * 小程序登录传入code
	 * <p>SysSocialDetails
	 * 通过code 调用小程序 获取唯一标识
	 * @param code
	 * @return
	 */
	@Override
	public String identify(String code) {
//		SysSocialDetails condition = new SysSocialDetails();
//		condition.setType(LoginTypeEnum.MINI_APP.getType());
//		SysSocialDetails socialDetails = sysSocialDetailsMapper.selectOne(new QueryWrapper<>(condition));
//
//		String url = String.format(SecurityConstants.MINI_APP_AUTHORIZATION_CODE_URL, socialDetails.getAppId(), socialDetails.getAppSecret(), code);
//		String result = HttpUtil.get(url);
//		log.debug("微信小程序响应报文:{}", result);
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
		SysSocialAuthUserEntity user = sysSocialAuthUserService.getOne(Wrappers.<SysSocialAuthUserEntity>query().lambda().eq(SysSocialAuthUserEntity::getLoginNo, openId));

		if (user == null) {
			log.info("微信小程序未绑定:{}", openId);
			return null;
		}
//		return sysSocialUserService.findUserInfo(user);
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
		List<SysSocialAuthUserEntity> userList = sysSocialAuthUserService.list(Wrappers.<SysSocialAuthUserEntity>query().lambda().eq(SysSocialAuthUserEntity::getLoginNo, identify));

		// 先把原有绑定关系去除,设置绑定为NULL
		if (CollUtil.isNotEmpty(userList)) {
			SysSocialAuthUserEntity condition = new SysSocialAuthUserEntity();
			condition.setLoginNo(identify);
			sysSocialAuthUserService.update(condition, Wrappers.<SysSocialAuthUserEntity>lambdaUpdate().set(SysSocialAuthUserEntity::getLoginNo, null));
			log.info("小程序账号 {} 更换账号绑定", identify);
		}

		user.setLoginNo(identify);
		sysSocialAuthUserService.updateById(user);
		return null;
	}

}
