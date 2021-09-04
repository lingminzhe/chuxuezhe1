package com.grgbanking.counter.common.security.service;

import com.grgbanking.counter.common.core.constant.CacheConstants;
import com.grgbanking.counter.common.security.service.GrgUser;
import com.grgbanking.counter.iam.api.dto.UserInfo;
import com.grgbanking.counter.iam.api.dubbo.RemoteUserService;
import com.grgbanking.counter.iam.api.entity.SysUserEntity;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;

/**
 * 用户详细信息
 */
@Slf4j
public class GrgUserDetailsServiceImpl implements GrgUserDetailsService {

    @DubboReference
    private RemoteUserService remoteUserService;

    @Autowired
    private CacheManager cacheManager;

    /**
     * 用户密码登录
     *
     * @param username 用户名
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    @SneakyThrows
    public UserDetails loadUserByUsername(String username) {
        Cache cache = cacheManager.getCache(CacheConstants.USER_DETAILS);
        if (cache != null && cache.get(username) != null) {
            return cache.get(username, GrgUser.class);
        }

        UserInfo result = remoteUserService.info(username);
        UserDetails userDetails = getUserDetails(result);
        cache.put(username, userDetails);
        return userDetails;
    }

    /**
     * 根据社交登录code 登录
     * @param inStr TYPE@CODE
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    @SneakyThrows
    public UserDetails loadUserBySocial(String inStr) {
		return getUserDetails(remoteUserService.social(inStr));
    }

    /**
     * 构建userdetails
     *
     * @return
     */
    private UserDetails getUserDetails(UserInfo info) {
        if (info == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(info.getPermissions());
        SysUserEntity user = info.getSysUser();
        boolean enabled = user.getEnabled() == 1;
        boolean lock = user.getLockFlag() == 0;
        // 构造security用户
        return new GrgUser(user.getUserId(), user.getDeptId(), user.getPhone(), user.getAvatar(),user.getUserName(),user.getPassword(), enabled, true, true, lock, authorities);
    }

}
