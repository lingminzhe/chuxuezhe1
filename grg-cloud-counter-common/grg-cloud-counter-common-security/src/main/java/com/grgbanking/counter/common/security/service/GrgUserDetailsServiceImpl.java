package com.grgbanking.counter.common.security.service;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.grgbanking.counter.common.core.constant.CacheConstants;
import com.grgbanking.counter.common.core.constant.CommonConstants;
import com.grgbanking.counter.common.security.base.GrgUser;
import com.grgbanking.counter.iam.api.bo.SysUserBo;
import com.grgbanking.counter.iam.api.bo.UserData;
import com.grgbanking.counter.iam.api.dubbo.GrgAuthService;
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
import java.util.HashSet;
import java.util.Set;

/**
 * 用户详细信息
 */
@Slf4j
public class GrgUserDetailsServiceImpl implements GrgUserDetailsService {

    @DubboReference
    private GrgAuthService grgAuthService;

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

        UserData result = grgAuthService.info(username);
        UserDetails userDetails = getUserDetails(result);
        cache.put(username, userDetails);
        return userDetails;
    }

    /**
     * 根据社交登录code 登录
     * 根据
     * @param inStr TYPE@CODE
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    @SneakyThrows
    public UserDetails loadUserBySocial(String inStr) {
        return null;
//		return getUserDetails(remoteUserService.social(inStr, SecurityConstants.FROM_IN));
    }

    /**
     * 构建userdetails
     *
     * @return
     */
    private UserDetails getUserDetails(UserData info) {
        if (info == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        Set<String> dbAuthsSet = new HashSet<>();
        if (ArrayUtil.isNotEmpty(info.getAuthorities())) {
//			// 获取角色
//			Arrays.stream(info.getRoles()).forEach(roleId -> dbAuthsSet.add(SecurityConstants.ROLE + roleId));
//			// 获取资源
            dbAuthsSet.addAll(info.getAuthorities());
        }
        Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(dbAuthsSet.toArray(new String[0]));
        SysUserBo user = info.getUserBo();
        boolean lock = !StrUtil.equals(user.getIsLocked(), CommonConstants.STATUS_NORMAL);
        // 构造security用户
        return new GrgUser(user.getId(), user.getUsername(), user.getPassword(), user.getMobileTelephone(), lock,true, true, true, authorities);
    }

}
