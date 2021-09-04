package com.grgbanking.counter.common.security.service;

import com.grgbanking.counter.common.core.constant.enums.LoginTypeEnum;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 */
public interface GrgUserDetailsService extends UserDetailsService {

	/**
	 * 根据社交登录code 登录
	 * @param loginTypeEnum	登录类型
	 * @param code	登录账号
	 * @return
	 * @throws UsernameNotFoundException
	 */
	UserDetails loadUserBySocial(LoginTypeEnum loginTypeEnum, String code) throws UsernameNotFoundException;

}
