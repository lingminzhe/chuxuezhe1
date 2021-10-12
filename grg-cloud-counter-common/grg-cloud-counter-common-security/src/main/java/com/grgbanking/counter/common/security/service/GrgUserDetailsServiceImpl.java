package com.grgbanking.counter.common.security.service;

import com.grgbanking.counter.common.core.constant.CacheConstants;
import com.grgbanking.counter.common.core.constant.enums.LoginTypeEnum;
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
//        String cacheKey = LoginTypeEnum.PWD.getType().concat(":").concat(username);
//        if (cache != null && cache.get(cacheKey) != null) {
//            return cache.get(cacheKey, GrgUser.class);
//        }
        UserInfo result = remoteUserService.info(username);
        UserDetails userDetails = getUserDetails(LoginTypeEnum.PWD,result);
//        cache.put(cacheKey, userDetails);
        return userDetails;
    }

    /**
     * 根据社交登录code 登录
     * @param loginTypeEnum	登录类型
     * @param code	登录账号
     * @return
     */
    @Override
    @SneakyThrows
    public UserDetails loadUserBySocial(LoginTypeEnum loginTypeEnum,String code) {
        Cache cache = cacheManager.getCache(CacheConstants.USER_DETAILS);
        String cacheKey = loginTypeEnum.getType().concat(":").concat(code);
        if (cache != null && cache.get(cacheKey) != null) {
            return cache.get(cacheKey, GrgUser.class);
        }
        UserInfo result = remoteUserService.social(loginTypeEnum,code);
        UserDetails userDetails = getUserDetails(loginTypeEnum,result);
        cache.put(cacheKey, userDetails);
        return userDetails;
    }


    /**
     * 构建userdetails
     *
     * @return
     */
    private UserDetails getUserDetails(LoginTypeEnum loginTypeEnum, UserInfo info) {
        if (info == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(info.getPermissions());
        SysUserEntity user = info.getSysUser();
        boolean enabled = user.getEnabled() == 1;
        boolean lock = user.getLockFlag() == 0;
        // 构造security用户
        return new GrgUser(loginTypeEnum,user.getUserId(), user.getDeptId(), user.getPhone(), user.getAvatar(),user.getUserName(),user.getPassword(), enabled, true, true, lock, authorities);
    }

}
