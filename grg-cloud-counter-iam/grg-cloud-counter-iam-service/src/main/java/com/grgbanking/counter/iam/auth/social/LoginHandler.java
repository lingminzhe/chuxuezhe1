package com.grgbanking.counter.iam.auth.social;


import com.grgbanking.counter.iam.api.dto.UserInfo;
import com.grgbanking.counter.iam.api.entity.SysSocialAuthUserEntity;

/**
 * 登录处理器
 */
public interface LoginHandler {

	/**
	 * 通过用户传入获取唯一标识
	 * @param code
	 * @return
	 */
	String identify(String code);

	/**
	 * 通过openId 获取用户信息
	 * @param identify
	 * @return
	 */
	UserInfo info(String identify);

	/**
	 * 处理方法
	 * @param code 登录参数
	 * @return
	 */
	UserInfo handle(String code);

	/**
	 * 绑定逻辑
	 * @param user 用户实体
	 * @param identify 渠道返回唯一标识
	 * @return
	 */
	Boolean bind(SysSocialAuthUserEntity user, String identify);

}
