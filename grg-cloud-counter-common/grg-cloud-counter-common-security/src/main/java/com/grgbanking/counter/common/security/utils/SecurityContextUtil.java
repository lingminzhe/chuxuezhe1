package com.grgbanking.counter.common.security.utils;

import com.alibaba.fastjson.JSON;
import com.grgbanking.counter.common.security.base.GrgUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Slf4j
public class SecurityContextUtil {

    private static String defaultPrefix = null;

    public static GrgUser getUserInfo() {
        GrgUser grgUser;

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof GrgUser) {
            grgUser = (GrgUser) principal;
        } else {
            log.error("Principal Class Loader is:");
            log.error(principal.getClass().getClassLoader().toString());
            log.error("UserInfo Class Loader is:");
            log.error(GrgUser.class.getClassLoader().toString());
            grgUser = JSON.parseObject(JSON.toJSONString(principal), GrgUser.class);
        }

        return grgUser;
    }

    public static String getUsername() {
        return getUserInfo().getUsername();
    }

    public static Long getUserId() {
        return getUserInfo().getUserId();
    }

    public static boolean hasAuthority(String roles) {
        return hasAnyAuthority(SecurityContextUtil.defaultPrefix, roles);
    }

    public static boolean hasAnyAuthority(String prefix, String... roles) {
        Set<String> roleSet = getAuthoritySet();

        for (String role : roles) {
            String defaultedRole = getRoleWithDefaultPrefix(prefix, role);
            if (roleSet.contains(defaultedRole)) {
                return true;
            }
        }

        return false;
    }

    private static String getRoleWithDefaultPrefix(String defaultRolePrefix, String role) {
        if (role == null) {
            return role;
        }
        if (defaultRolePrefix == null || defaultRolePrefix.length() == 0) {
            return role;
        }
        if (role.startsWith(defaultRolePrefix)) {
            return role;
        }
        return defaultRolePrefix + role;
    }

    private static Set<String> getAuthoritySet() {
        Set<String> roles = null;
        if (roles == null) {
            Collection<? extends GrantedAuthority> userAuthorities = getUserInfo().getAuthorities();
            roles = AuthorityUtils.authorityListToSet(userAuthorities);
        }

        return roles;
    }

    public static boolean hasAdminRole(GrgUser grgUser) {
        if (grgUser == null) {
            grgUser = getUserInfo();
        }

        List<Long> roleIdList = grgUser.getRoleIdList();

        Long adminRoleId = PlatformAdminRoleIdUtil.getPlatformAdminRoleId();

        for (Long roleId : roleIdList) {
            if (adminRoleId.equals(roleId)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 具有超管角色权限
     *
     * @return
     */
    public static boolean hasAdminRole() {
        return hasAdminRole(getUserInfo());
    }

    /**
     * 不具有超管角色权限
     *
     * @return
     */
    public static boolean notAdminRole() {
        return !hasAdminRole(getUserInfo());
    }

    public static String isExistUser(String username){
        if(StringUtils.isEmpty(username)) {
            username = SecurityContextUtil.getUsername();
        }
        return username;
    }

    public static String getDefaultPrefix() {
        return SecurityContextUtil.defaultPrefix;
    }

    public static void setDefaultPrefix(String defaultPrefix) {
        SecurityContextUtil.defaultPrefix = defaultPrefix;
    }

}
